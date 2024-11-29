/*
 * Copyright 2024 Atomic Wire Technology Limited
 * SPDX-License-Identifier: Apache-2.0
 */
package org.example.orchestra;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import quickfix.Application;
import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.MemoryStoreFactory;
import quickfix.Message;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionSettings;
import quickfix.SocketAcceptor;
import quickfix.field.CorporateBuyback;
import quickfix.field.MsgType;
import quickfix.field.Rule80A;
import quickfix.field.SecurityID;
import quickfix.field.Side;

@Slf4j
public class QuickFixEngineDataDictionaryApp implements Application {

  @Override
  public void onCreate(SessionID sessionId) {
    log.info("Session created: {}", sessionId);
  }

  @Override
  public void onLogon(SessionID sessionId) {
    log.info("Logon to session: {}", sessionId);
  }

  @Override
  public void onLogout(SessionID sessionId) {
    log.info("Logout from session: {}", prettyLog(sessionId));
  }

  @Override
  public void toAdmin(Message message, SessionID sessionId) {
    log.info("Sending admin message: {}", prettyLog(message));
  }

  @Override
  public void fromAdmin(Message message, SessionID sessionId) {
    log.info("Received admin message: {}", prettyLog(message));
  }

  @Override
  public void toApp(Message message, SessionID sessionId) {
    log.info("Sending application message: {}", prettyLog(message));
  }

  @Override
  public void fromApp(Message message, SessionID sessionId)
      throws FieldNotFound, IncorrectTagValue, IncorrectDataFormat {
    try (var session = Session.lookupSession(sessionId)) {
      // The validation here is not strictly necessary as the FIX engine has already validated
      // against AppDataDictionary. If however, the custom Data Dictionary contains new msgTypes
      // that are not in base FIX, the FIXT transport layer will reject the messages. In this
      // case it's possible to set ValidateIncomingMessage=N in the QuickFIX config, and perform
      // the validation here
      log.info("Validating incoming message against custom Data Dictionary");
      var dataDictionary =
          session
              .getDataDictionaryProvider()
              .getApplicationDataDictionary(session.getSenderDefaultApplicationVersionID());
      dataDictionary.validate(message);
      log.info(
          "Message type {} successfully validated against custom Data Dictionary",
          message.getHeader().getString(MsgType.FIELD));
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (IncorrectTagValue e) {
      log.error("Message validation failed due to tag: {}", e.getMessage());
      throw e;
    } catch (IncorrectDataFormat e) {
      log.error("Message validation failed due to data format: {}", e.getMessage());
      throw e;
    }

    // Perform application specific processing
    if ((MsgType.NEW_ORDER_SINGLE).equals(message.getHeader().getString(MsgType.FIELD))) {
      char side = message.getChar(Side.FIELD);
      log.info(
          "NewOrderSingle received: Symbol={}, Side={}, CorporateBuyBack={}, Rule80A={}",
          message.getString(SecurityID.FIELD),
          (side == '1' ? "Buy" : "Sell"),
          message.getChar(CorporateBuyback.FIELD),
          message.getChar(Rule80A.FIELD));
    }
  }

  /** Replaces the SOH in messages with pipes for readability in log messages */
  public static String prettyLog(Object message) {
    return message.toString().replace('\u0001', '|');
  }

  public static void main(String[] args) throws ConfigError, InterruptedException {
    checkArgs(args);

    // Load the session settings from a configuration file
    var settings = new SessionSettings("quickfix-server.cfg");
    // the Data Dictionary is passed in
    log.info("Using data dictionary at {}", args[0]);
    // when using AppDataDictionary, FIXT is used for the session layer
    settings.setString("AppDataDictionary", args[0]);

    // Create the FIX engine instance
    var engineApp = new QuickFixEngineDataDictionaryApp();
    // Create a message store factory (memory-based in this example)
    var storeFactory = new MemoryStoreFactory();

    var acceptor =
        new SocketAcceptor(engineApp, storeFactory, settings, null, new DefaultMessageFactory());
    acceptor.start();

    log.info("FIX engine started. Waiting 2 minutes for incoming messages...");
    Thread.sleep(120000);
    acceptor.stop();
  }

  public static void checkArgs(String[] args) {
    if (args.length < 1) {
      log.error("Error: You must provide a path to the QuickFIX Data Dictionary as an argument");
      System.exit(1);
    }

    if (!Files.isRegularFile(Paths.get(args[0]))) {
      log.error("Error: The QuickFIX Data Dictionary file {} does not exist", args[0]);
      System.exit(1);
    }
  }
}
