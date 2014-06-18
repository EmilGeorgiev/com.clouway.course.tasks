package com.clouway.suite;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import org.junit.Test;

/**
 * Created by clouway on 6/10/14.
 */
public class SampleTest {

  public static interface MyInterface{
    String getName(String name);
  }


  public static class TargetClass {
    private final MyInterface myInterface;

    @Inject
    public TargetClass(MyInterface myInterface) {
      this.myInterface = myInterface;
    }

    public void test(String name) {
      System.out.println(myInterface.getName(name));
    }
  }

  @Test
  public void testTest() throws Exception {
    Injector injector = Guice.createInjector(new AbstractModule() {
      @Override
      protected void configure() {

      }

      @Provides
      @Named("prefix")
      public String getPrefix() {
        return "name: ";
      }

      @Provides
      public MyInterface getMyInterface(@Named("prefix") final String prefix) {
        return new MyInterface() {
          @Override
          public String getName(String name) {
            return prefix + name;
          }
        };
      }
    });

    TargetClass targetClass = injector.getInstance(TargetClass.class);
    targetClass.test("Bla");
  }
}
