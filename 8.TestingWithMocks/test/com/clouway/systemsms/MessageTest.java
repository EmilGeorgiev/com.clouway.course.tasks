package com.clouway.systemsms;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;

import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by clouway on 12/27/13.
 */
public class MessageTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  
  @Mock
  ValidatorSystem mockSystem;

  @Test
  public void testSendCorrectMessage() throws Exception {
    final String recipient = "Emil Georgiev";
    final String title = "Work";
    final String text = "24, 25, and 26 will not work";
    final Message message = new Message(recipient, title, text);
    context.checking(new Expectations() {{
      oneOf(mockSystem).validateText(text);
      will(returnValue(true));
      oneOf(mockSystem).validateMessage(title, recipient);
      will(returnValue(true));
    }
    });

    assertThat(message.send(mockSystem), is("Message is send"));
  }

  @Test(expected = InvalidTextMessageException.class)
  public void testSendMessageWithTextOfMoreThan120Characters() throws Exception {
    String recipient = "Emil Georgiev";
    String title = "Work";
    final String text = "111111111111111111111111111111" +
            "111111111111111111111111111111" +
            "111111111111111111111111111111" +
            "1111111111111111111111111111111";
    final Message message = new Message(recipient, title, text);
    context.checking(new Expectations() {{
      oneOf(mockSystem).validateText(text);
      will(returnValue(false));
    }});

    message.send(mockSystem);
  }

  @Test(expected = MissingArgumentException.class)
  public void testSendMessageWithNullTitle() throws Exception {
    final String recipient = "Emil Georgiev";
    final String title = null;
    final String text = "24, 25, and 26 will not work";
    Message message = new Message(recipient, title, text);
    context.checking(new Expectations() {{
      oneOf(mockSystem).validateText(text);
      will(returnValue(true));
      oneOf(mockSystem).validateMessage(title, recipient);
      will(returnValue(false));
    }
    });

    message.send(mockSystem);
  }
}
