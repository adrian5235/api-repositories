package com.adrian.api.repository;

import com.adrian.api.model.Branch;
import com.adrian.api.model.Repository;
import com.google.gson.Gson;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;

@org.springframework.stereotype.Repository
public class BranchRepository {

    public List<Branch> getBranches(String username, Repository repository) throws IOException {
        String url = "https://api.github.com";
        Gson gson = new Gson();

        String json = Jsoup.connect(url + "/repos/" + username + "/" + repository.getName() + "/branches")
                .ignoreContentType(true).execute().body();
        Branch[] branches = gson.fromJson(json, Branch[].class);

        return List.of(branches);
    }
}
