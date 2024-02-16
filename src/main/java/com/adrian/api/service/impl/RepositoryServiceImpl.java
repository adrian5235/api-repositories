package com.adrian.api.service.impl;

import com.adrian.api.exception.UserDoesNotExistException;
import com.adrian.api.model.Branch;
import com.adrian.api.model.Repository;
import com.adrian.api.service.RepositoryService;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RepositoryServiceImpl implements RepositoryService {
    String url = "https://api.github.com";
    Gson gson = new Gson();
    String json;

    // get all the user GitHub repositories by a given username
    @Override
    public List<Repository> getByUsername(String username) throws IOException, UserDoesNotExistException {

        // check if the GitHub user exists
        try {
            Jsoup.connect(url + "/users/" + username).ignoreContentType(true).get();
        } catch (Exception e) {
            throw new UserDoesNotExistException();
        }

        List<Repository> repositories = new ArrayList<>(getRepositories(username));
        List<Repository> forks = new ArrayList<>();

        // for each repository which isn't a fork, set its branches and their last commit sha
        for (Repository repository : repositories) {
            if (repository.isFork()) {
                forks.add(repository);
            } else {
                repository.setBranches(getBranches(username, repository));
            }
        }

        // remove all repos which are forks
        repositories.removeAll(forks);

        return repositories;
    }

    // get user repositories
    private List<Repository> getRepositories(String username) throws IOException {
        json = Jsoup.connect(url + "/users/" + username + "/repos")
                .ignoreContentType(true).execute().body();
        Repository[] repos = gson.fromJson(json, Repository[].class);

        return List.of(repos);
    }

    // get user repository branches
    private List<Branch> getBranches(String username, Repository repository) throws IOException {
        json = Jsoup.connect(url + "/repos/" + username + "/" + repository.getName() + "/branches")
                .ignoreContentType(true).execute().body();
        Branch[] branches = gson.fromJson(json, Branch[].class);

        return List.of(branches);
    }
}