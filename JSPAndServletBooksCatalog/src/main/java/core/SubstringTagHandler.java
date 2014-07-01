package core;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by clouway on 6/30/14.
 */
public class SubstringTagHandler extends TagSupport {
  private String input;

  @Override
  public int doStartTag() throws JspException {

    try {

      JspWriter out = pageContext.getOut();

      out.println(input);

    } catch (IOException e) {
      e.printStackTrace();
    }
    return SKIP_BODY;

  }

  public String getInput() {
    return input;
  }

  public void setInput(String input) {
    this.input = input;
  }

}
