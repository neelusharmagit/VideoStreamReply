package com.reply.videostream.validator;

import com.google.common.base.Strings;
import com.reply.videostream.model.Payment;
import com.reply.videostream.model.UserDetail;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;


@Component
public class VideoServiceValidation {

    public boolean checkUserDetails(UserDetail userDetail) {

        //Check Mandatory fields
        if (Strings.isNullOrEmpty(userDetail.getUserName()) || Strings.isNullOrEmpty( userDetail.getPassword())
                || (Strings.isNullOrEmpty(userDetail.getEmail())) || (userDetail.getDob() == null)) {
            return false;
        }

       boolean isValidCard = true;
        if(!Strings.isNullOrEmpty(userDetail.getCreditCardNum())) {
            isValidCard = isValidCrediCard(userDetail.getCreditCardNum());
        }
        return isValidUserName(userDetail.getUserName()) && isValidEmail(userDetail.getEmail()) && isValidPassword(userDetail.getPassword()) && isIsoDate(userDetail.getDob())  && isValidCard;
    }

    public boolean isValidUserName(String userName) {
        // Regex to check string is alphanumeric or not.
        boolean isAlphaNumeric = !userName.contains(" ")  &&
                userName.chars().allMatch(Character::isLetterOrDigit);
        return isAlphaNumeric;
    }

    public boolean isValidPassword(String password) {
            String passwordReg = "(?=.*[0-9])(?=.*[A-Z]).{8,}";
            return password.matches(passwordReg);


    }


    public boolean isValidEmail(String email) {
        String emailregex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(emailregex);
    }


    public boolean isIsoDate(String date) {
        try {
              LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean isValidCrediCard(String creditCardnum) {
        return  !Strings.isNullOrEmpty(creditCardnum) && creditCardnum.length() == 16;
    }

    public boolean validateAge(UserDetail userDetail) {
        if (!Strings.isNullOrEmpty(userDetail.getDob())) {
            LocalDate today = LocalDate.now();
            LocalDate birthday = LocalDate.parse(userDetail.getDob());

            Period p = Period.between(birthday, today);
            return p.getYears() > 18;
        } else {
            return false;
        }
    }

    public boolean isValidPayment(Payment payment){
       if((Strings.isNullOrEmpty(payment.getCardNum())) && (Strings.isNullOrEmpty(payment.getAmount()))){
           return false;
        }
      return (payment.getAmount().length() == 3) && isValidCrediCard(payment.getCardNum());
    }
}

