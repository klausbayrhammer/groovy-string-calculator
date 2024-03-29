/**
 * @author Klaus Bayrhammer
 */
class StringCalculator {

    private static final String DEFAULT_DELIMITER = "[\n,]"

    private String delimiter
    private String numbersWithDelimiters
    private final String input;
    private ArrayList parsedNumbers = []

    public StringCalculator(String input) {
        this.input = input
    }

    static int add(String input) {
        new StringCalculator(input).add()
    }

    int add() {
        if (isInputEmpty())
            return 0

        handleDelimiter()
        parseNumbers()
        calculateSum()
    }

    private boolean isInputEmpty() {
        input.isEmpty()
    }

    private void handleDelimiter() {
        if (hasCustomDelimiter()) {
            parseCustomDelimiter()
        } else {
            useDefaultDelimiter()
        }
    }

    private boolean hasCustomDelimiter() {
        input.startsWith("//")
    }

    private void useDefaultDelimiter() {
        numbersWithDelimiters = input
        delimiter = DEFAULT_DELIMITER
    }

    private void parseCustomDelimiter() {
        def endOfFirstLine = input.indexOf("\n")
        List customDelimitors = input.substring(3, endOfFirstLine - 1).split("\\]\\[")
        delimiter = customDelimitors.join("|")
        numbersWithDelimiters = input.substring(endOfFirstLine + 1)
    }

    private List parseNumbers() {
        numbersWithDelimiters.split(delimiter).each { token ->
            parsedNumbers << new ParsedNumber(token)
        }
    }

    private int calculateSum() {
        int result = 0;
        List negativeValues = []
        parsedNumbers.each { number ->
            if (!number.ignored)
                result += number.value
            if (number.illegal)
                negativeValues << number.value
        }
        if (!negativeValues.isEmpty()) {
            throw new IllegalArgumentException(String.format('negatives not supported (%s)', negativeValues.join(",")))
        }
        result
    }

    static class ParsedNumber {
        final int value;
        final boolean ignored;
        final boolean illegal;

        ParsedNumber(String strValue) {
            this.value = Integer.valueOf(strValue)
            if (value > 1000)
                ignored = true;
            if (value < 0)
                illegal = true;
        }
    }
}
