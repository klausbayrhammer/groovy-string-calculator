import org.junit.Test

import static StringCalculator.*
import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.assertThat
import static org.junit.matchers.JUnitMatchers.containsString

/**
 * @author Klaus Bayrhammer
 */
class StringCalculatorTest {

    @Test
    void addWithEmptyString() {
        assertThat add(""), is(0)
    }

    @Test
    void addWithSingleValidInput() {
        assertThat add("1"), is(1)
    }

    @Test
    void addWithTwoValidInputsSeparatedByCommas() {
        assertThat add("1,2"), is(3)
    }

    @Test
    void addWithTwoValidInputsSeparatedByNewlines() {
        assertThat add("1\n2"), is(3)
    }

    @Test
    void addWithTwoValidInputsSeparatedByCustomDelimiter() {
        assertThat add("//[x]\n1x2"), is(3)
    }

    @Test
    void addWithSingleNegativeValue() {
        try {
            add("-1")
        } catch (IllegalArgumentException iae) {
            assertThat iae.getMessage(), containsString("negatives not supported (-1)")
        }
    }

    @Test
    void addWithMultipleNegativeValues() {
        try {
            add("-1,-2")
        } catch (IllegalArgumentException iae) {
            assertThat iae.getMessage(), containsString("negatives not supported (-1,-2)")
        }
    }

    @Test
    void addWithNumberLargerThanThousand() {
        assertThat add("1,1001"), is(1)
    }
}
