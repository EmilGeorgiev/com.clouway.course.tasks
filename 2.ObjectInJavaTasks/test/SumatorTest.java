import com.clouway.sumator.Sumator;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
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

    assertThat(actual, is(8));
  }

  @Test(expected = IllegalArgumentException.class)
  public void suppliedIncorrectData() {
    sumator.sum("abc", "5");
  }

  @Test(expected = IllegalArgumentException.class)
  public void submissionNullAgrument() throws Exception {
      sumator.sum("5", null);
  }
}
