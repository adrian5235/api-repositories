package com.adrian.api.service.impl;

import com.adrian.api.dto.RepositoryDTO;
import com.adrian.api.exception.UserNotFoundException;
import com.adrian.api.mapper.RepositoryMapper;
import com.adrian.api.model.Repository;
import com.adrian.api.repository.BranchRepository;
import com.adrian.api.repository.RepositoryRepository;
import com.adrian.api.service.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {

    private final RepositoryRepository repositoryRepository;
    private final BranchRepository branchRepository;
    private final RepositoryMapper mapper;

    // get all the user GitHub repositories which aren't forks by a given username
    @Override
    public List<RepositoryDTO> getByUsername(String username) throws UserNotFoundException {
        List<Repository> repositories = new ArrayList<>(repositoryRepository.getRepositories(username));
        List<Repository> forks = new ArrayList<>();

        if (!repositories.isEmpty()) {
            // for each repository which isn't a fork, set its branches
            for (Repository repository : repositories) {
                if (repository.isFork()) {
                    forks.add(repository);
                } else {
                    repository.setBranches(branchRepository.getBranches(username, repository));
                }
            }

            // remove all repos which are forks
            repositories.removeAll(forks);

            // cast repos to DTOs and return them
            return repositories
                    .stream()
                    .map(mapper::toDTO)
                    .collect(Collectors.toList());
        }

        // return empty list if there are no repos
        return new ArrayList<>();
    }
}