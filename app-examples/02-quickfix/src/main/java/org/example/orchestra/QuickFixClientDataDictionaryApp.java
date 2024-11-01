/*
 * Copyright 2024 Atomic Wire Technology Limited
 * SPDX-License-Identifier: Apache-2.0
 */
package org.example.orchestra;

import static org.example.orchestra.QuickFixEngineDataDictionaryApp.checkArgs;
import static org.example.orchestra.QuickFixEngineDataDictionaryApp.prettyLog;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import quickfix.*;
import quickfix.field.*;

@Slf4j
public class QuickFixClientDataDictionaryApp implements Application {

  @Override
  public void onCreate(SessionID sessionId) {
    log.info("Client session created: {}", prettyLog(sessionId));
  }

  @Override
  public void onLogon(SessionID sessionId) {
    log.info("Client logged on: {}", prettyLog(sessionId));
  }

  @Override
  public void onLogout(SessionID sessionId) {
    log.info("Client logged out: {}", prettyLog(sessionId));
  }

  @Override
  public void toAdmin(Message message, SessionID sessionId) {
    log.info("Client sending admin message: {}", prettyLog(message));
  }

  @Override
  public void fromAdmin(Message message, SessionID sessionId) {
    log.info("Client received admin message: {}", prettyLog(message));
  }

  @Override
  public void toApp(Message message, SessionID sessionId) {
    log.info("Client sending application message: {}", prettyLog(message));
  }

  @Override
  public void fromApp(Message message, SessionID sessionId) {
    log.info("Client received application message: {}", prettyLog(message));
  }

  public void sendValidRequest(SessionID sessionID) throws SessionNotFound {
    var validRequest = getBaseMessage(true);
    Session.sendToTarget(validRequest, sessionID);
  }

  /** Method to send messages that don't conform to the data dictionary */
  public void sendInvalidRequests(SessionID sessionID) throws SessionNotFound {
    var missingRequiredFieldMessage = getBaseMessage(false);
    Session.sendToTarget(missingRequiredFieldMessage, sessionID);

    var invalidFieldMessage = getBaseMessage(true);
    invalidFieldMessage.setField(new SecondaryOrderID("ZYX987"));
    Session.sendToTarget(invalidFieldMessage, sessionID);
  }

  private Message getBaseMessage(Boolean includeAccountField) {
    var message = new Message();

    message.getHeader().setField(new MsgType(MsgType.ORDER_SINGLE));

    if (includeAccountField) {
      message.setField(new Account("ACC123"));
    }
    message.setField(new ClOrdID("ORD12123"));
    message.setField(new Side(Side.SELL));
    message.setField(new TransactTime(LocalDateTime.now()));
    message.setField(new OrdType(OrdType.MARKET));
    message.setField(new SecurityID("AAPL"));
    message.setField(new OrderQty(3500));

    return message;
  }

  public static void main(String[] args) throws ConfigError, SessionNotFound, InterruptedException {
    checkArgs(args);

    // Load the client session settings from a configuration file
    var settings = new SessionSettings("quickfix-client.cfg");
    // the Data Dictionary is passed in - it's the path to the output of the
    // basic-examples/08-quickfix sub-project
    settings.setString("AppDataDictionary", args[0]);

    // Create the FIX client application instance
    var clientApp = new QuickFixClientDataDictionaryApp();
    // Create a message store factory (memory-based in this example)
    var storeFactory = new MemoryStoreFactory();

    // Initialize the client initiator with all components
    var initiator =
        new SocketInitiator(clientApp, storeFactory, settings, null, new DefaultMessageFactory());
    initiator.start();

    // Allow some time to establish a connection
    Thread.sleep(2000);

    // This example application only has a single session configured
    var sessionID = initiator.getSessions().get(0);

    log.info("Sending valid request");
    clientApp.sendValidRequest(sessionID);

    log.info("Sending invalid requests");
    clientApp.sendInvalidRequests(sessionID);

    // Sleep to allow responses to be received
    Thread.sleep(1000);
    initiator.stop();
  }
}
