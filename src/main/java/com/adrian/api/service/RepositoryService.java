package com.adrian.api.service;

import com.adrian.api.dto.RepositoryDTO;
import com.adrian.api.exception.UserDoesNotExistException;

import java.io.IOException;
import java.util.List;

public interface RepositoryService {
    List<RepositoryDTO> getByUsername(String username) throws IOException, UserDoesNotExistException;
}
