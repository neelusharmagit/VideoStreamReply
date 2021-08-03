package com.reply.videostream.service;

import com.google.common.base.Strings;
import com.reply.videostream.exception.InvalidAgeException;
import com.reply.videostream.exception.NoUserFoundException;
import com.reply.videostream.exception.ValidationFailureException;
import com.reply.videostream.model.Payment;
import com.reply.videostream.model.UserDetail;
import com.reply.videostream.repository.VideoUserRepository;
import com.reply.videostream.validator.VideoServiceValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoStreamService {


    @Autowired
    private VideoUserRepository videoUserRepository;


    @Autowired
    private VideoServiceValidation videoServiceValidation;


    public List<UserDetail> allUsers(String isCreditCard) {
        List<UserDetail> allUsers = videoUserRepository.getUserDetailList();
        if(allUsers == null || allUsers.isEmpty()){
            throw new NoUserFoundException("No Users Available" );
        }
        if(Strings.isNullOrEmpty(isCreditCard))
            return allUsers;
        if(isCreditCard.equalsIgnoreCase("Yes")){
            return allUsers.stream().filter( e -> e.getCreditCardNum() != null).collect(Collectors.toList());
        } else if(isCreditCard.equalsIgnoreCase("No")){
            return allUsers.stream().filter( e -> e.getCreditCardNum() == null).collect(Collectors.toList());
        }
        return allUsers;
    }


    public UserDetail addUser(UserDetail users)  {
        if(!videoServiceValidation.checkUserDetails(users)){
            throw new ValidationFailureException("Incorrect User Details");
        }
        if(!videoServiceValidation.validateAge(users)){
            throw new InvalidAgeException("Invalid Age");
        }
        videoUserRepository.addUser(users);
        return users;
    }

    public Payment makePayment(Payment payment){
        if(!videoServiceValidation.isValidPayment(payment)){
            throw new ValidationFailureException("Invalid Payment details");
        }
        videoUserRepository.addPayment(payment);
        return payment;
    }
}
