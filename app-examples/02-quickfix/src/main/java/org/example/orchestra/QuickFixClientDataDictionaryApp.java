/*
 * Copyright 2024 Atomic Wire Technology Limited
 * SPDX-License-Identifier: Apache-2.0
 */
package org.example.orchestra;

import static org.example.orchestra.QuickFixEngineDataDictionaryApp.checkArgs;
import static org.example.orchestra.QuickFixEngineDataDictionaryApp.prettyLog;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import quickfix.Application;
import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.MemoryStoreFactory;
import quickfix.Message;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.SessionSettings;
import quickfix.SocketInitiator;
import quickfix.field.Account;
import quickfix.field.ClOrdID;
import quickfix.field.CorporateBuyback;
import quickfix.field.OrdType;
import quickfix.field.OrderQty;
import quickfix.field.Rule80A;
import quickfix.field.SecondaryOrderID;
import quickfix.field.SecurityID;
import quickfix.field.SecurityIDSource;
import quickfix.field.Side;
import quickfix.field.TransactTime;
import quickfix.fix44.NewOrderSingle;
import quickfix.fix44.component.Instrument;
import quickfix.fix44.component.OrderQtyData;

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
    var message = new NewOrderSingle();

    if (includeAccountField) {
      message.set(new Account("ACC123"));
    }
    message.set(new ClOrdID("ORD12123"));
    message.set(new Side(Side.SELL));
    message.set(new TransactTime(LocalDateTime.now()));
    message.set(new OrdType(OrdType.MARKET));
    var orderQtyData = new OrderQtyData();
    orderQtyData.set(new OrderQty(3500));
    message.set(orderQtyData);
    // custom fields from the Orchestra spec
    message.set(new CorporateBuyback(CorporateBuyback.PERMITTED));
    message.set(new Rule80A(Rule80A.PRINCIPAL));
    var instrument = new Instrument();
    instrument.set(new SecurityID("AAPL"));
    instrument.set(new SecurityIDSource(SecurityIDSource.ISIN));
    message.set(instrument);

    return message;
  }

  public static void main(String[] args) throws ConfigError, SessionNotFound, InterruptedException {
    checkArgs(args);

    // Load the client session settings from a configuration file
    var settings = new SessionSettings("quickfix-client.cfg");
    // the Data Dictionary is passed in, when using AppDataDictionary, FIXT is used for the session
    // layer
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
