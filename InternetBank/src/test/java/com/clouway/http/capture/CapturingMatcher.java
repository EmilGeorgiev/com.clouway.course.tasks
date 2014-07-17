package com.clouway.http.capture;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by clouway on 7/16/14.
 */
public class CapturingMatcher<T> extends BaseMatcher<T> {

  private final Matcher<T> baseMatcher;

  private Object captureArg;

  public CapturingMatcher(Matcher<T> baseMatcher) {
    this.baseMatcher = baseMatcher;
  }

  public Object getCaptureArg() {
    return captureArg;
  }

  @Override
  public boolean matches(Object item) {
    captureArg = item;
    return baseMatcher.matches(item);
  }

  @Override
  public void describeTo(Description description) {
    baseMatcher.describeTo(description);
  }
}
