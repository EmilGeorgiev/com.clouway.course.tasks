import com.clouway.sumator.Sumator;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
//import static org.junit.Assert.*;
/**
 * Created by clouway on 12/20/13.
 */
public class SumatorTest {
  private Sumator sumator;
  @Before
  public void setUp() {
    sumator = new Sumator();
  }

  @Test
  public void testReturnsCorrectResultWithNormalData() throws Exception {
    int actual = sumator.sum("3", "5");
    int expected = 8;

    assertEquals(expected, actual);
  }

  @Test(expected = NumberFormatException.class)
  public void submissionOfInvalidData() {
    sumator.sum("abc", "5");
  }

  @Test(expected = IllegalArgumentException.class)
  public void submissionNullAgrument() throws Exception {
      sumator.sum("5", null);
  }
}
