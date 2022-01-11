package com.mrppak.spring_project01.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrppak.spring_project01.springboot.web.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class PostsApiControllerTest {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mvc;

    @Test
    public void saveTest() throws Exception {

        // given
        String body = mapper.writeValueAsString(
                PostsSaveRequestDto.builder()
                        .title("Test title")
                        .content("Test content")
                        .author("Test author")
                        .build()
        );

        // when
        ResultActions resultAction = mvc.perform(
                MockMvcRequestBuilders.post("/api/v1/posts")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultAction
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }
}
