package com.adrian.api.service;

import com.adrian.api.exception.UserDoesNotExistException;
import com.adrian.api.model.Repository;

import java.io.IOException;
import java.util.List;

public interface RepositoryService {
    List<Repository> getByUsername(String username) throws IOException, UserDoesNotExistException;
}
