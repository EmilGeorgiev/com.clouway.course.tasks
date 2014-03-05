package com.clouway.suportingmultipleclients;

import com.clouway.suportingmultipleclients.fake.MockServerMessageListener;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 2/11/14.
 */
public class ServerTest {

  private Server server;
  private MockServerMessageListener serverMessageListener;
  private DummyClient dummyClient;
  private DummyClient dummyClient2;

  Mockery mockery = new JUnit4Mockery() {{
    setThreadingPolicy(new Synchroniser());
  }};

  ServerMessages serverMessages = mockery.mock(ServerMessages.class);

  @Before
  public void setUp() {
    serverMessageListener = new MockServerMessageListener();
    server = new Server(serverMessages, serverMessageListener, 2323);
    dummyClient = new DummyClient(2323);
    dummyClient2 = new DummyClient(2323);
  }

  @After
  public void closeServer() {
    server.stopServer();
  }

  @Test
  public void newClientWasConnected() throws Exception {

    mockery.checking(new Expectations() {
      {
        oneOf(serverMessages).connectNewClient(1);
        will(returnValue("The server is connected clients 1."));

        oneOf(serverMessages).sendFirstMessageToNewClient(1);
      }
    });

    startServer();

    dummyClient.connect();

    List<String> firstServerMessage = serverMessageListener.getServerDisplayMessages();

    assertThat(firstServerMessage, hasItems("The server is connected clients 1."));

  }

  @Test
  public void clientNumberIsIncremented() throws Exception {

    mockery.checking(new Expectations() {
      {
        oneOf(serverMessages).connectNewClient(1);
        will(returnValue("The server is connected clients 1."));

        oneOf(serverMessages).sendFirstMessageToNewClient(1);

        oneOf(serverMessages).connectNewClient(2);
        will(returnValue("The server is connected clients 2."));

        oneOf(serverMessages).sendFirstMessageToNewClient(2);

        oneOf(serverMessages).sendMessageToAllClient(2);
      }
    });

    startServer();

    dummyClient.connect();

    List<String> firstServerMessage = serverMessageListener.getServerDisplayMessages();

    dummyClient2.connect();

    List<String> allServerMessages = serverMessageListener.getServerDisplayMessages();

    assertThat(firstServerMessage, hasItems("The server is connected clients 1."));
    assertThat(allServerMessages, hasItems("The server is connected clients 1.", "The server is connected clients 2."));
    assertThat(serverMessageListener.listMessage.size(), is(2));

  }

  @Test
  public void sendMessageToClientThatHasConnected() throws Exception {

    mockery.checking(new Expectations() {{
      oneOf(serverMessages).connectNewClient(1);
      oneOf(serverMessages).sendFirstMessageToNewClient(1);
      will(returnValue("You are client number 1"));
    }
    });

    startServer();

    dummyClient.connect();

    String contentResponse = dummyClient.getResponse();

    assertThat(contentResponse, is("You are client number 1"));

  }

  @Test
  public void sendMessageToClientsWhenNewClientWasConnected() throws Exception {

    mockery.checking(new Expectations() {{
      oneOf(serverMessages).connectNewClient(1);

      oneOf(serverMessages).sendFirstMessageToNewClient(1);
      will(returnValue("You are client number 1"));

      oneOf(serverMessages).connectNewClient(2);

      oneOf(serverMessages).sendFirstMessageToNewClient(2);
      will(returnValue("You are client number 2"));

      oneOf(serverMessages).sendMessageToAllClient(2);
      will(returnValue("Client number 2 is connected"));
    }
    });

    startServer();

    dummyClient.connect();

    String client1FirstResponse = dummyClient.getResponse();

    dummyClient2.connect();


    String client2FirstResponse = dummyClient2.getResponse();
    String client1SecondResponse = dummyClient.getResponse();

    assertThat(client1FirstResponse, is("You are client number 1"));
    assertThat(client2FirstResponse, is("You are client number 2"));
    assertThat(client1SecondResponse, is("You are client number 1" +
            "Client number 2 is connected"));

  }

  private void startServer() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        server.startServer();
      }
    }).start();
  }
}
