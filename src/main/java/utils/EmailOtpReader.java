package utils;

public class EmailOtpReader {

    /**
     * Returns the OTP for automation runs.
     *
     * The dev team provides a dedicated test account with a fixed OTP
     * for automation purposes, so this reads that value from
     * config.properties instead of polling a real mailbox.
     *
     * If the team later switches to a real, randomly generated OTP,
     * replace the body of this method with real IMAP polling logic
     * (with retries, since email delivery is not instant) - the page
     * objects and tests do not need to change either way, since they
     * only depend on this method returning a String.
     */
    public String getLatestOtp() {

        String otp = ConfigReader.getProperty("test.otp");

        if (otp == null || otp.isBlank()) {

            throw new RuntimeException(
                    "test.otp is not set in config.properties");
        }

        return otp;
    }
}