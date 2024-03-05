package com.adrian.api.mapper;

import com.adrian.api.dto.BranchDTO;
import com.adrian.api.model.Branch;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper implements Mapper <Branch, BranchDTO> {

    @Override
    public BranchDTO toDTO(Branch branch) {
        if (branch == null) {
            throw new NullPointerException("The branch is null");
        }
        String name = branch.name();
        String lastCommitSha = branch.commit().sha();

        return new BranchDTO(name, lastCommitSha);
    }
}
