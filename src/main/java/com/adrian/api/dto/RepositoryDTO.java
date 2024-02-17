package com.adrian.api.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record RepositoryDTO(
        String name,
        String owner,
        List<BranchDTO> branches
) {}
