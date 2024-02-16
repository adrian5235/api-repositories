package com.adrian.api.controller;

import com.adrian.api.dto.RepositoryDTO;
import com.adrian.api.exception.UserDoesNotExistException;
import com.adrian.api.mapper.RepositoryMapper;
import com.adrian.api.service.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/repositories")
public class RepositoryController {

    private final RepositoryService service;
    private final RepositoryMapper mapper;

    @GetMapping("/{username}")
    public ResponseEntity<List<RepositoryDTO>> getByUsername(@PathVariable String username)
            throws IOException, UserDoesNotExistException {
        return new ResponseEntity<>(
                service.getByUsername(username)
                        .stream()
                        .map(mapper::toDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }
}