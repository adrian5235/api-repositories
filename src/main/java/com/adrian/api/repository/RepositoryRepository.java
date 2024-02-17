package com.adrian.api.repository;

import com.adrian.api.model.Repository;
import com.google.gson.Gson;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;

// this is repository class containing method to fetch all the user GitHub repositories
@org.springframework.stereotype.Repository
public class RepositoryRepository {

    public List<Repository> getRepositories(String username) throws IOException {
        String url = "https://api.github.com";
        Gson gson = new Gson();

        String json = Jsoup.connect(url + "/users/" + username + "/repos")
                .ignoreContentType(true).execute().body();
        Repository[] repos = gson.fromJson(json, Repository[].class);

        return List.of(repos);
    }
}
