package com.reply.videostream.validation;

import com.reply.videostream.model.Payment;
import com.reply.videostream.model.UserDetail;
import com.reply.videostream.validator.VideoServiceValidation;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VideoServiceValidationTest {

    VideoServiceValidation videoServiceValidation;

    @BeforeEach
    public void setUp(){
        videoServiceValidation = new VideoServiceValidation();
    }

    @ParameterizedTest
    @ValueSource(strings = {"validUser2", "ab435", "user1234"})
    void isValidUserName(String userName) {
        assertTrue(videoServiceValidation.isValidUserName(userName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"valid User2", "ab&&&435", "user$£$yt"})
    void isNotValidUserName(String userName) {
        assertFalse(videoServiceValidation.isValidUserName(userName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcA123h", "user4RTR", "valdUser5"})
    void isValidPassword(String password) {
        assertTrue(videoServiceValidation.isValidPassword(password));
    }
    @ParameterizedTest
    @ValueSource(strings = {"abcd", "a  b&&&435", "user$£$yt"})
    void isNotValidPassword(String password) {
        assertFalse(videoServiceValidation.isValidPassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcd@yahoo.com", "ab80_hj@yahoo.com", "adaf@yahoo.co.uk"})
    void isValidEmail(String email) {
        assertTrue(videoServiceValidation.isValidEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcd", "@b&&&435", "avc.com"})
    void isNotValidEmail(String email) {
        assertFalse(videoServiceValidation.isValidPassword(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2009/19/20", "24-08-29", "2009-JAN-21" , "2019-14-22"})
    void isNotValidDOB(String email) {
        assertFalse(videoServiceValidation.isIsoDate(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2019-11-20", "2018-09-16"})
    void isValidDOB(String email) {
        assertTrue(videoServiceValidation.isIsoDate(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"9090909090909090", "5675786786786786"})
    void isValidCardNum(String cardNum) {
        assertTrue(videoServiceValidation.isValidCrediCard(cardNum));
    }

    @ParameterizedTest
    @ValueSource(strings = {"318765432678987653333333", "3187654326"})
    void isNotValidCardNum(String cardNum) {
        assertFalse(videoServiceValidation.isValidCrediCard(cardNum));
    }

    @Test
    void testCheckUserDetails()
    {
      UserDetail validUser = new UserDetail("userName1","validPwd!0", "validUser@valid.com", "2019-09-22" );
      assertTrue(videoServiceValidation.checkUserDetails(validUser));
    }

    @Test
    void testCheckInvalidUserDetails()
    {
        UserDetail validUser = new UserDetail("userName1","validPwd!0", "vali@dUser@valid.com", "2019-07-21" );
        assertFalse(videoServiceValidation.checkUserDetails(validUser));
    }

    @Test
    void testCheckValidUserAge()
    {
        UserDetail validUser = new UserDetail("userName1","validPwd!0", "vali@dUser@valid.com", "2000-08-21" );
        UserDetail invalidUser = new UserDetail("userName1","validPwd!0", "vali@dUser@valid.com", "2009-08-21" );
        assertTrue(videoServiceValidation.validateAge(validUser));
        assertFalse(videoServiceValidation.validateAge(invalidUser));
    }

    @Test
    void isValidPayment() {
        assertFalse(videoServiceValidation.isValidPayment(new Payment(null , "100")));
        assertFalse(videoServiceValidation.isValidPayment(new Payment("1234567891122334" , "")));
        assertFalse(videoServiceValidation.isValidPayment(new Payment("12345678911223349890809" , "100")));
        assertFalse(videoServiceValidation.isValidPayment(new Payment("1234567891122334" , "10")));
        assertTrue(videoServiceValidation.isValidPayment(new Payment("1234567891122334" , "100")));
    }

}
