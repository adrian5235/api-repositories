package com.adrian.api.controller;

import com.adrian.api.dto.RepositoryDTO;
import com.adrian.api.exception.UserNotFoundException;
import com.adrian.api.service.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/repositories")
public class RepositoryController {

    private final RepositoryService service;

    @GetMapping("/{username}")
    public ResponseEntity<List<RepositoryDTO>> getByUsername(@PathVariable String username)
            throws UserNotFoundException {
        return new ResponseEntity<>(service.getByUsername(username), HttpStatus.OK);
    }
}