/**
 * @author Klaus Bayrhammer
 */
class StringCalculator {

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
        input.split(",").each { token ->
            result += Integer.valueOf(token)
        }
        result
    }
}
