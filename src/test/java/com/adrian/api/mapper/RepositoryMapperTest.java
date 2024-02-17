package com.adrian.api.mapper;

import com.adrian.api.dto.BranchDTO;
import com.adrian.api.dto.RepositoryDTO;
import com.adrian.api.model.Branch;
import com.adrian.api.model.Commit;
import com.adrian.api.model.Owner;
import com.adrian.api.model.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryMapperTest {

    private RepositoryMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new RepositoryMapper(new BranchMapper());
    }

    @Test
    public void should_map_repository_to_repositoryDTO() {
        Repository repository = Repository.builder()
                .name("repo1")
                .owner(new Owner("owner1"))
                .branches(Arrays.asList(
                        new Branch("branch1", new Commit("sha1")),
                        new Branch("branch2", new Commit("sha2"))
                ))
                .build();

        RepositoryDTO repositoryDTO = mapper.toDTO(repository);
        List<BranchDTO> branchDTOS = repositoryDTO.branches();

        assertEquals(repository.getName(), repositoryDTO.name());
        assertEquals(repository.getOwner().getLogin(), repositoryDTO.owner());

        for (BranchDTO branchDTO : branchDTOS) {
            assertInstanceOf(branchDTO.getClass(), branchDTO);
        }
    }

    @Test
    public void should_throw_exception_when_repository_is_null() {
        var exception = assertThrows(NullPointerException.class,
                () -> mapper.toDTO(null));
        assertEquals("The repository is null", exception.getMessage());
    }
}