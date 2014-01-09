package com.clouway.systemsms;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 12/27/13.
 */
public class SenderTest {

  private String recipient;
  private String title;
  private String text;
  private Sender sender;
  private String sendMessage;

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  Validator validator;

  @Mock
  SmsSystem smsSystem;

  @Before
  public void setUp() {
    sendMessage = "Message is send";
    sender = new Sender(smsSystem, validator, sendMessage);
    recipient = "Emil Georgiev";
    title = "Work";
    text = "24, 25, and 26 will not work";
  }

  @Test
  public void sendMessageWhitCorrectData() throws Exception {
    final Message message = new Message(recipient, title, text);
    context.checking(new Expectations() {
      {
        oneOf(validator).validateMessage(message);
        oneOf(smsSystem).send(message);
      }
    });

    assertThat(sender.sendMessage(message), is(sender.getInfoForSend()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void sendMessageWithLongText() throws Exception {
    final String text = "111111111111111111111111111111" +
            "111111111111111111111111111111" +
            "111111111111111111111111111111" +
            "1111111111111111111111111111111";
    final Message message = new Message(recipient, title, text);
    context.checking(new Expectations() {{
      oneOf(validator).validateMessage(message);
      will(throwException(new IllegalArgumentException()));
    }});

    sender.sendMessage(message);
  }

  @Test(expected = IllegalArgumentException.class)
  public void sendAMessageWhitMissingTitle() throws Exception {
    final String title = null;
    final Message message = new Message(recipient, title, text);
    context.checking(new Expectations() {
      {
        oneOf(validator).validateMessage(message);
        will(throwException(new IllegalArgumentException()));
      }
    });

    sender.sendMessage(message);
  }

  @Test(expected = IllegalArgumentException.class)
  public void sendMessageWhitMissingRecipient() throws Exception {
    final String recipient = null;
    final Message message = new Message(recipient, title, text);
    context.checking(new Expectations() {
      {
        oneOf(validator).validateMessage(message);
        will(throwException(new IllegalArgumentException()));
      }
    });

    sender.sendMessage(message);
  }
}
