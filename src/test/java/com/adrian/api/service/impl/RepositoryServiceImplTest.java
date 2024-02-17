package com.adrian.api.service.impl;

import com.adrian.api.dto.RepositoryDTO;
import com.adrian.api.exception.UserDoesNotExistException;
import com.adrian.api.mapper.RepositoryMapper;
import com.adrian.api.model.Branch;
import com.adrian.api.model.Commit;
import com.adrian.api.model.Owner;
import com.adrian.api.model.Repository;
import com.adrian.api.repository.BranchRepository;
import com.adrian.api.repository.RepositoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class RepositoryServiceImplTest {

    @InjectMocks
    private RepositoryServiceImpl service;
    @Mock
    private RepositoryMapper mapper;
    @Mock
    private RepositoryRepository repositoryRepository;
    @Mock
    private BranchRepository branchRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_return_all_user_repositories_which_are_not_forks()
            throws IOException, UserDoesNotExistException {

        // Given
        List<Repository> repositories = Arrays.asList(
                // first one, not a fork
                Repository.builder()
                        .name("repo1")
                        .owner(new Owner("owner1"))
                        .fork(false)
                        .branches(Arrays.asList(
                                new Branch("branch1", new Commit("sha1")),
                                new Branch("branch2", new Commit("sha2"))
                        ))
                        .build(),
                // second one, a fork
                Repository.builder()
                        .name("repo2")
                        .owner(new Owner("owner1"))
                        .fork(true)
                        .branches(Arrays.asList(
                                new Branch("branch1", new Commit("sha1")),
                                new Branch("branch2", new Commit("sha2"))
                        ))
                        .build()
        );

        // Mock the call
        when(repositoryRepository.getRepositories(anyString())).thenReturn(repositories);

        // When
        List<RepositoryDTO> repositoryDTOS = service.getByUsername("adrian5235");

        // Then
        // expected size of the repositories is -1 because of one of them being a fork -
        // service method is supposed to return only repositories which aren't forks
        assertEquals(repositories.size() - 1, repositoryDTOS.size());
        verify(repositoryRepository, times(1)).getRepositories(anyString());
    }

    @Test
    public void should_throw_user_does_not_exist_exception_when_github_user_of_given_username_does_not_exist() {
        assertThrows(UserDoesNotExistException.class, () -> service.getByUsername("adrian52353"));
    }

    // TODO create a github account without any repositories or with only fork/forks and write a test
    //  checking if exception of class NullPointerException with proper message is being thrown
}