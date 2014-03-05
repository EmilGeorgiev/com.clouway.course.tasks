package mytest;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by clouway on 2/27/14.
 */
public class SimpleTest {

  interface Display {
    void display(String message);
  }

  @Test
  public void test() throws Exception {

    Display display = (Display) Proxy.newProxyInstance(SimpleTest.class.getClassLoader(), new Class[]{Display.class}, new InvocationHandler() {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(args[0]);
        return null;
      }
    });

    display.display("Hello");


  }
}
