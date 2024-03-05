package com.adrian.api.repository;

import com.adrian.api.exception.UserNotFoundException;
import com.adrian.api.model.Repository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

@org.springframework.stereotype.Repository
public class RepositoryRepository {

    RestClient restClient = RestClient.builder().baseUrl("https://api.github.com/").build();

    public List<Repository> getRepositories(String username) throws UserNotFoundException {
        List<Repository> repositories;

        try {
            repositories = restClient.get()
                    .uri("/users/" + username + "/repos")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
        } catch (HttpClientErrorException e) {
            throw new UserNotFoundException();
        }

        return repositories;
    }
}
