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

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  Validator validator;

  @Mock
  SystemSms systemSms;

  @Before
  public void setUp() {
    sender = new Sender(systemSms, validator);
    recipient = "Emil Georgiev";
    title = "Work";
    text = "24, 25, and 26 will not work";
  }

  @Test
  public void sendMessageWhitCorrectData() throws Exception {
    final Message message = new Message(recipient, title, text);
    context.checking(new Expectations() {
      {
        oneOf(validator).validateText(text);
        oneOf(validator).validateTitle(title);
        oneOf(validator).validateRecipient(recipient);
        oneOf(systemSms).send(message);
      }
    });

    assertThat(sender.sendMessage(message), is("Message is send"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void sendMessageWithIncorrectText() throws Exception {
    final String text = "111111111111111111111111111111" +
            "111111111111111111111111111111" +
            "111111111111111111111111111111" +
            "1111111111111111111111111111111";
    final Message message = new Message(recipient, title, text);
    context.checking(new Expectations() {{
      oneOf(validator).validateText(text);
      will(throwException(new IllegalArgumentException()));
    }});

    sender.sendMessage(message);
  }

  @Test(expected = IllegalArgumentException.class)
  public void sendAMessageWhitMissingTitle() throws Exception {
    final String title = null;
    Message message = new Message(recipient, title, text);
    context.checking(new Expectations() {
      {
        oneOf(validator).validateText(text);
        oneOf(validator).validateTitle(title);
        will(throwException(new IllegalArgumentException()));
      }
    });

    sender.sendMessage(message);
  }

  @Test(expected = IllegalArgumentException.class)
  public void sendMessageWhitMissingRecipient() throws Exception {
    final String recipient = null;
    Message message = new Message(recipient, title, text);
    context.checking(new Expectations() {
      {
        oneOf(validator).validateText(text);
        oneOf(validator).validateTitle(title);
        oneOf(validator).validateRecipient(recipient);
        will(throwException(new IllegalArgumentException()));
      }
    });

    sender.sendMessage(message);
  }
}
