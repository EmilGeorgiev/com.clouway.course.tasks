package com.clouway.suportingmultipleclients;


import com.clouway.suportingmultipleclients.fake.MockClientMessageListener;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 2/12/14.
 */
public class ClientTest {
  private DummyServer dummyServer;
  private Client client;

  Mockery mockery = new JUnit4Mockery() {{
    setThreadingPolicy(new Synchroniser());
  }};

  @Rule
  public ExpectedException exception = ExpectedException.none();

  UserMessages userMessages = mockery.mock(UserMessages.class);

  @Before
  public void setUp() {
    dummyServer = new DummyServer("You are client number: 1", 4444);

    MockClientMessageListener serverMessageListener = new MockClientMessageListener();

    client = new Client(userMessages, serverMessageListener, 4444);
  }

  @Test
  public void readMessagesFromServer() throws Exception {

    mockery.checking(new Expectations() {
      {
        oneOf(userMessages).connectClient();
      }
    });

    new Thread(new Runnable() {
      @Override
      public void run() {
        dummyServer.startServer();
      }
    }).start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        client.connectToServer();
      }
    }).start();

    String contentResponse = client.getResponse();

    assertThat(contentResponse, is("You are client number: 1"));
  }

  @Test
  public void serverWasStopped() throws Exception {

    mockery.checking(new Expectations() {
      {
        ignoring(userMessages);
      }
    });


    Thread serverThread = new Thread(new Runnable() {
      @Override
      public void run() {
        dummyServer.startServer();
      }
    });
    serverThread.setPriority(10);
    serverThread.start();

    Thread clientThread = new Thread(new Runnable() {
      @Override
      public void run() {
        exception.expect(NoSocketException.class);
        client.connectToServer();
      }
    });

    clientThread.setPriority(1);
    clientThread.start();

    dummyServer.stop();
  }
}
