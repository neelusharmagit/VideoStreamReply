package com.reply.videostream.repository;


import com.reply.videostream.exception.DuplicateUserException;
import com.reply.videostream.exception.ValidationFailureException;
import com.reply.videostream.model.Payment;
import com.reply.videostream.model.UserDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VideoStreamRepositoryTest {

    VideoUserRepository videoUserRepository;

    List<UserDetail> alluserDetailList = new ArrayList<>();
    List<UserDetail> userDetailListWithCard= new ArrayList<>();
    List<UserDetail> userDetailListWithoutCard= new ArrayList<>();
    List<UserDetail> emptyList= new ArrayList<>();
    UserDetail validUser;
    UserDetail validUserwithCard;
    UserDetail invalidUser;
    Payment payment;

    @BeforeEach
    public void setUp(){
        videoUserRepository = new VideoUserRepository();
        validUser = new UserDetail("userName1","validPwd!0", "validUser@valid.com", "2009-08-06" );
        validUserwithCard = new UserDetail("userName3","validPwd!0", "validUser@valid.com", "2018-05-21" );
        validUserwithCard.setCreditCardNum("1234567891011121314");
        alluserDetailList.add(validUser);
        alluserDetailList.add(validUserwithCard);
        userDetailListWithCard.add(validUserwithCard);
        userDetailListWithoutCard.add(validUser);
    }

    @Test
    public void testAddingUser() {
        videoUserRepository.addUser(validUserwithCard);
        assertEquals(validUserwithCard.getUserName(), videoUserRepository.getUserDetailList().get(0).getUserName());
    }

    @Test
    public void testAddingDuplicateUser(){
        videoUserRepository.addUser(validUser);
        assertEquals(validUser.getUserName(),videoUserRepository.getUserDetailList().get(0).getUserName());
        DuplicateUserException thrown = assertThrows(
                DuplicateUserException.class,
                () -> videoUserRepository.addUser(validUser),
                "User Already exist"
        );
    }
}
