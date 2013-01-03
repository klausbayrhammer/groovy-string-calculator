/**
 * @author Klaus Bayrhammer
 */
class StringCalculator {

    static int add(String input) {
        new InnerCalculator(input).add()
    }

    static class InnerCalculator {

        private static final String DEFAULT_DELIMITER = "[\n,]"

        private String delimiter
        private String numbersWithDelimiters
        private final String input;

        public InnerCalculator(String input) {
            this.input = input
        }

        int add() {
            if (isInputEmpty())
                return 0

            handleDelimiter()
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
            delimiter = input.charAt(3)
            numbersWithDelimiters = input.substring(6)
        }

        private int calculateSum() {
            List negativeValues = []
            int result = 0;
            numbersWithDelimiters.split(delimiter).each { token ->
                def intValue = Integer.valueOf(token)
                if (intValue < 0) {
                    negativeValues << intValue
                }
                result += intValue
            }
            if (!negativeValues.isEmpty()) {
                throw new IllegalArgumentException(String.format('negatives not supported (%s)', negativeValues.join(",")))
            }
            result
        }
    }
}
