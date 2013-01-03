import org.junit.Before
import org.junit.Test

import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.assertThat
/**
 * @author Klaus Bayrhammer
 */
class StringCalculatorTest {

    private StringCalculator calculator

    @Before
    void setUp() {
        calculator = new StringCalculator()
    }

    @Test
    void addWithEmptyString() {
        assertThat calculator.add(""), is(0)
    }

    @Test
    void addWithSingleValidInput() {
        assertThat calculator.add("1"), is(1)
    }

    @Test
    void addWithTwoValidInputsSeparatedByCommas() {
        assertThat calculator.add("1,2"), is(3)
    }

    @Test
    void addWithTwoValidInputsSeparatedByNewlines() {
        assertThat calculator.add("1\n2"), is(3)
    }

    @Test
    void addWithTwoValidInputsSeparatedByCustomDelimiter() {
        assertThat calculator.add("//[x]\n1x2"), is(3)
    }


}
