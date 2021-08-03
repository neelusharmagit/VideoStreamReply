package com.reply.videostream.controller;

import com.reply.videostream.model.Payment;
import com.reply.videostream.model.UserDetail;
import com.reply.videostream.service.VideoStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VideoStreamController {

    @Autowired
    private VideoStreamService videoStreamService;


    @GetMapping("/api/v1/users")
    public ResponseEntity<List<UserDetail>> listAllUsers(@RequestParam(required = false) String isCreditCard) {
        List<UserDetail> allUsers = videoStreamService.allUsers(isCreditCard);
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PostMapping("/api/v1/users")
    public ResponseEntity<UserDetail> addUser(@RequestBody UserDetail userDetail) {
        UserDetail createdUser = videoStreamService.addUser(userDetail);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/api/v1/makePayment")
    public ResponseEntity<Payment> makePayment(@RequestBody Payment payment) {
        Payment successfulpayment = videoStreamService.makePayment(payment);
        return new ResponseEntity<>(successfulpayment, HttpStatus.CREATED);
    }
}
