package com.reply.videostream.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;



@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetail {


    private String userName;

    public UserDetail(String userName, String password, String email, String dob) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCreditCardNum() {
        return creditCardNum;
    }

    public void setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    private String password;
    private String email;
    private String dob;
    private String creditCardNum;

}
