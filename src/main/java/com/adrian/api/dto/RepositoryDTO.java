package com.adrian.api.dto;

import java.util.List;

public record RepositoryDTO(
        String name,
        String owner,
        List<BranchDTO> branches
) {}
