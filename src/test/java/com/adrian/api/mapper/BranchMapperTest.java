package com.adrian.api.mapper;

import com.adrian.api.dto.BranchDTO;
import com.adrian.api.model.Branch;
import com.adrian.api.model.Commit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BranchMapperTest {

    private BranchMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new BranchMapper();
    }

    @Test
    public void should_map_branch_to_branchDTO() {
        Branch branch = Branch.builder()
                .name("branch1")
                .commit(new Commit("sha1"))
                .build();

        BranchDTO branchDTO = mapper.toDTO(branch);

        assertEquals(branch.getName(), branchDTO.name());
        assertEquals(branch.getCommit().getSha(), branchDTO.lastCommitSha());
    }

    @Test
    public void should_throw_exception_when_branch_is_null() {
        var exception = assertThrows(NullPointerException.class, () -> mapper.toDTO(null));
        assertEquals("The branch is null", exception.getMessage());
    }
}