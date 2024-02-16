package com.adrian.api.mapper;

import com.adrian.api.dto.BranchDTO;
import com.adrian.api.dto.RepositoryDTO;
import com.adrian.api.model.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RepositoryMapper {

    private final BranchMapper mapper;

    public RepositoryDTO toDto(Repository repository) {
        String name = repository.getName();
        String owner = repository.getOwner().getLogin();
        List<BranchDTO> branches = repository.getBranches()
                .stream()
                .map(mapper::toDTO)
                .toList();

        return new RepositoryDTO(name, owner, branches);
    }
}
