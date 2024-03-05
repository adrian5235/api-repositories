package com.adrian.api.repository;

import com.adrian.api.exception.UserNotFoundException;
import com.adrian.api.model.Branch;
import com.adrian.api.model.Repository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

@org.springframework.stereotype.Repository
public class BranchRepository {

    RestClient restClient = RestClient.builder().baseUrl("https://api.github.com/").build();

    public List<Branch> getBranches(String username, Repository repository) throws UserNotFoundException {
        List<Branch> branches;

        try {
            branches = restClient.get()
                    .uri("/repos/" + username + "/" + repository.getName() + "/branches")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
        } catch (HttpClientErrorException e) {
            throw new UserNotFoundException();
        }

        return branches;
    }
}
