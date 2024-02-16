package com.adrian.api.mapper;

import com.adrian.api.dto.BranchDTO;
import com.adrian.api.model.Branch;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper {

    public BranchDTO toDTO(Branch branch) {
        String name = branch.getName();
        String lastCommitSha = branch.getCommit().getSha();

        return new BranchDTO(name, lastCommitSha);
    }
}
