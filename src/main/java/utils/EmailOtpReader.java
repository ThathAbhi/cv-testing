package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailOtpReader {

    public String getLatestOtp() {

        // Connect to mailbox using IMAP

        // Read latest unread email

        // Extract OTP using regex

        // Example:

        String emailContent =
                "Your OTP is 845632";

        Pattern pattern =
                Pattern.compile("\\d{6}");

        Matcher matcher =
                pattern.matcher(emailContent);

        if(matcher.find()){

            return matcher.group();
        }

        return null;
    }
}
