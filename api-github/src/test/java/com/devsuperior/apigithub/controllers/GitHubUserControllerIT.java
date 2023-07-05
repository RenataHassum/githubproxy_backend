package com.devsuperior.apigithub.controllers;

import com.devsuperior.apigithub.dto.GitHubUserPageDTO;
import com.devsuperior.apigithub.services.GitHubUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class GitHubUserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GitHubUserService service;

    @Test
    public void testFindAllPage_ReturnsUserPage() throws Exception {
        // Arrange
        Long sinceId = 30L;
        GitHubUserPageDTO expectedPage = service.getGitHubUsersPage(sinceId);

        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .param("since", String.valueOf(sinceId)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(expectedPage.getContent().size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.next").value(expectedPage.getNext()));
    }




}