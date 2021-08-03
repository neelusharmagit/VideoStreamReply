package com.reply.videostream.repository;

import com.reply.videostream.exception.DuplicateUserException;
import com.reply.videostream.model.Payment;
import com.reply.videostream.model.UserDetail;
import org.springframework.stereotype.Repository;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class VideoUserRepository {

    public  List<UserDetail> getUserDetailList() {
        List<UserDetail> userList = new ArrayList<>();
        userDetailList.entrySet().stream().forEach( e -> userList.add(e.getValue()));
        return userList;

    }


    private static Map<String,UserDetail> userDetailList = new HashMap<>();

    public void addUser(UserDetail userDetail){
        if(userDetailList.containsKey(userDetail.getUserName())){
            throw new DuplicateUserException("User Already exist");
        }
        userDetailList.put(userDetail.getUserName(),userDetail);
    }

    private static List<Payment> paymentList = new ArrayList<>();

    public void addPayment(Payment payment){
        paymentList.add(payment);
    }
}
