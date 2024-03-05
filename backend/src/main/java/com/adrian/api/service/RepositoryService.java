package com.adrian.api.service;

import com.adrian.api.dto.RepositoryDTO;
import com.adrian.api.exception.UserNotFoundException;

import java.util.List;

public interface RepositoryService {
    List<RepositoryDTO> getByUsername(String username) throws UserNotFoundException;
}
