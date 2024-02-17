package com.adrian.api.service.impl;

import com.adrian.api.dto.RepositoryDTO;
import com.adrian.api.exception.UserDoesNotExistException;
import com.adrian.api.mapper.RepositoryMapper;
import com.adrian.api.model.Repository;
import com.adrian.api.repository.BranchRepository;
import com.adrian.api.repository.RepositoryRepository;
import com.adrian.api.service.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {

    private final RepositoryMapper mapper;
    private final RepositoryRepository repositoryRepository;
    private final BranchRepository branchRepository;

    // get all the user GitHub repositories which aren't forks by a given username
    @Override
    public List<RepositoryDTO> getByUsername(String username) throws IOException, UserDoesNotExistException {

        // check if the GitHub user exists
        try {
            Jsoup.connect("https://api.github.com/users/" + username).ignoreContentType(true).get();
        } catch (Exception e) {
            throw new UserDoesNotExistException();
        }

        List<Repository> repositories = new ArrayList<>(repositoryRepository.getRepositories(username));
        if (repositories.isEmpty()) {
            throw new NullPointerException("User doesn't have any repositories");
        }
        List<Repository> forks = new ArrayList<>();

        // for each repository which isn't a fork, set its branches and their last commit sha
        for (Repository repository : repositories) {
            if (repository.isFork()) {
                forks.add(repository);
            } else {
                repository.setBranches(branchRepository.getBranches(username, repository));
            }
        }

        // remove all repos which are forks
        repositories.removeAll(forks);

        return repositories
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}