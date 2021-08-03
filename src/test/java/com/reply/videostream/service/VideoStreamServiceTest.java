package com.reply.videostream.service;

import com.reply.videostream.exception.InvalidAgeException;
import com.reply.videostream.exception.NoUserFoundException;
import com.reply.videostream.exception.ValidationFailureException;
import com.reply.videostream.model.Payment;
import com.reply.videostream.model.UserDetail;
import com.reply.videostream.repository.VideoUserRepository;
import com.reply.videostream.validator.VideoServiceValidation;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VideoStreamServiceTest {

    @Mock
    VideoUserRepository videoUserRepository;

    @Mock
    VideoServiceValidation videoServiceValidation;

    @InjectMocks
    VideoStreamService videoStreamService;

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
        validUser = new UserDetail("userName1","validPwd!0", "validUser@valid.com", "2019-05-08" );
        invalidUser = new UserDetail("userName2","validPwd!0", "validUser@valid.com", "2019-05-08" );
        validUserwithCard = new UserDetail("userName3","validPwd!0", "validUser@valid.com", "2019-05-08" );
        validUserwithCard.setCreditCardNum("1234567891011121314");
        alluserDetailList.add(validUser);
        alluserDetailList.add(validUserwithCard);
        userDetailListWithCard.add(validUserwithCard);
        userDetailListWithoutCard.add(validUser);
    }

    @Test
    public void testValidfindAll(){
        Mockito.when(videoUserRepository.getUserDetailList()).thenReturn(alluserDetailList);
        List<UserDetail> userDetailList = videoStreamService.allUsers(null);
        assertFalse(userDetailList.isEmpty());
        assertEquals(2,userDetailList.size());
    }

    @Test
    public void testValidfindWithCard(){
        Mockito.when(videoUserRepository.getUserDetailList()).thenReturn(alluserDetailList);
        List<UserDetail> userDetailList = videoStreamService.allUsers("Yes");
        assertFalse(userDetailList.isEmpty());
        assertEquals(1,userDetailList.size());
        UserDetail userDetail = userDetailList.get(0);
        assertTrue(userDetail.getUserName().equals("userName3"));
    }

    @Test
    public void testValidfindWithoutCard(){
        Mockito.when(videoUserRepository.getUserDetailList()).thenReturn(alluserDetailList);
        List<UserDetail> userDetailList = videoStreamService.allUsers("No");
        assertFalse(userDetailList.isEmpty());
        assertEquals(1,userDetailList.size());
        UserDetail userDetail = userDetailList.get(0);
        assertTrue(userDetail.getUserName().equals("userName1"));
    }


    @Test
    void testNoUsersFound() {
        NoUserFoundException thrown = assertThrows(
                NoUserFoundException.class,
                () -> videoStreamService.allUsers(null),
                "No Users Available"
        );
    }


    @Test
    void testValidAddUser(){
        Mockito.when(videoServiceValidation.checkUserDetails(validUser)).thenReturn(true);
        Mockito.when(videoServiceValidation.validateAge(validUser)).thenReturn(true);
        UserDetail createdUser = videoStreamService.addUser(validUser);
        assertEquals(createdUser,validUser);
    }


    @Test
    void testVInValidAddUser(){
        ValidationFailureException thrown = assertThrows(
                ValidationFailureException.class,
                () -> videoStreamService.addUser(invalidUser),
                "Incorrect User Details"
        );
    }


    @Test
    void testvalidMakePayment(){
        Mockito.when(videoServiceValidation.isValidPayment(payment)).thenReturn(true);
        Payment createdPayment = videoStreamService.makePayment(payment);
        assertEquals(createdPayment,payment);
    }

    @Test
    void testVInValidPayment(){
        ValidationFailureException thrown = assertThrows(
                ValidationFailureException.class,
                () -> videoStreamService.makePayment(payment),
                "Incorrect User Details"
        );
    }

    @Test
    void testVInValidAget(){
        Mockito.when(videoServiceValidation.checkUserDetails(validUser)).thenReturn(true);
        InvalidAgeException thrown = assertThrows(
                InvalidAgeException.class,
                () -> videoStreamService.addUser(validUser),
                "Incorrect User Details"
        );
    }
}
