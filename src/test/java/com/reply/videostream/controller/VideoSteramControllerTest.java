package com.reply.videostream.controller;

import com.reply.videostream.exception.NoUserFoundException;
import com.reply.videostream.exception.ValidationFailureException;
import com.reply.videostream.model.UserDetail;
import com.reply.videostream.service.VideoStreamService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = VideoStreamController.class)
public class VideoSteramControllerTest {

    @Autowired
    private WebApplicationContext context;


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoStreamService videoStreamService;

    List<UserDetail> userDetailList;

    @Test
    public void testFetchAllUsers() throws Exception {

            String uri = "/api/v1/users";
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

            int status = mvcResult.getResponse().getStatus();
            assertEquals(200, status);
            String content = mvcResult.getResponse().getContentAsString();
            assertNotNull(content);
    }


    @Test
    public void addValidUser() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(
                        "    {\n" +
                                "        \"userName\": \"33\",\n" +
                                "        \"password\": \"Dangote\",\n" +
                                "        \"email\": \"500\",\n" +
                                "        \"dob\" : \"2019-12-22\"" +
                                "    }")).andReturn().getResponse();
        assertEquals(response.getStatus(),(HttpStatus.CREATED.value()));
    }

    @Test
    public void addValidPayment() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                post("/api/v1/makePayment").contentType(MediaType.APPLICATION_JSON).content(
                        "    {\n" +
                                "        \"userName\": \"33\",\n" +
                                "        \"password\": \"Dangote\",\n" +
                                "        \"email\": \"500\",\n" +
                                "        \"dob\" : \"2019-12-22\"" +
                                "    }")).andReturn().getResponse();
        assertEquals(response.getStatus(),(HttpStatus.CREATED.value()));
    }



}
