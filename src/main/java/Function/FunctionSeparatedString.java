package Function;

public class FunctionSeparatedString {

    public static void main(String[] args) {
        int OTP = 1234;

        // Convert the integer OTP to a string
        String otpString = Integer.toString(OTP);

        // Get the first character of the OTP string
        char firstChar = otpString.charAt(0);

        // Print the first character
        System.out.println(String.valueOf(firstChar));

}
}
