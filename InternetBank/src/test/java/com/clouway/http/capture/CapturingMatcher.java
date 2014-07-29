package com.clouway.http.capture;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by clouway on 7/16/14.
 */
public class CapturingMatcher<T> extends BaseMatcher<T> {

  private final Matcher<T> baseMatcher;

  private Object capturedArg;

  public CapturingMatcher(Matcher<T> baseMatcher){
    this.baseMatcher = baseMatcher;
  }

  public Object getCapturedArgument(){
    return capturedArg;
  }

  public boolean matches(Object arg){
    capturedArg = arg;
    return baseMatcher.matches(arg);
  }

  public void describeTo(Description arg){
    baseMatcher.describeTo(arg);
  }
}
