/**
 * @author Klaus Bayrhammer
 */
class StringCalculator {

    private String delimiter = "[\n,]"

    int add(String input) {
        if (isInputEmpty(input))
            return 0

        calculateSum(input)
    }

    private boolean isInputEmpty(String input) {
        input.isEmpty()
    }

    private int calculateSum(String input) {
        int result = 0;
        String numbersWithDelimiters

        if (hasCustomDelimiter(input)) {
            numbersWithDelimiters = parseCustomDelimiter(input)
        } else {
            numbersWithDelimiters = input
        }
        numbersWithDelimiters.split(delimiter).each { token ->
            result += Integer.valueOf(token)
        }
        result
    }

    private String parseCustomDelimiter(String input) {
        delimiter = input.charAt(3)
        input.substring(6)
    }

    private boolean hasCustomDelimiter(String input) {
        input.startsWith("//")
    }
}
