package com.devsuperior.apigithub.controllers;

import com.devsuperior.apigithub.dto.GitHubUserDetailsDTO;
import com.devsuperior.apigithub.dto.GitHubUserPageDTO;
import com.devsuperior.apigithub.services.GitHubUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class GitHibUserController {

    @Autowired
    private GitHubUserService service;

    @GetMapping(value = "/users")
    public ResponseEntity<GitHubUserPageDTO> findAllPage(
            @RequestParam(name = "since", defaultValue = "0") Long sinceId) {
        GitHubUserPageDTO dto = service.getGitHubUsersPage(sinceId);
        return ResponseEntity.ok(dto);
    }


    @GetMapping(value = "/users/{username}/details")
    public ResponseEntity<GitHubUserDetailsDTO> findUserDetails(@PathVariable String username) {
        GitHubUserDetailsDTO dto = service.getGitHubUserDetails(username);
        return ResponseEntity.ok(dto);
    }
}
