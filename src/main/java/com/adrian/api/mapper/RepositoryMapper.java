package com.adrian.api.mapper;

import com.adrian.api.dto.BranchDTO;
import com.adrian.api.dto.RepositoryDTO;
import com.adrian.api.model.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RepositoryMapper implements Mapper<Repository, RepositoryDTO> {

    private final BranchMapper mapper;

    @Override
    public RepositoryDTO toDTO(Repository repository) {
        if (repository == null) {
            throw new NullPointerException("The repository is null");
        }
        String name = repository.getName();
        String owner = repository.getOwner().getLogin();
        List<BranchDTO> branches = repository.getBranches()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        return new RepositoryDTO(name, owner, branches);
    }
}
