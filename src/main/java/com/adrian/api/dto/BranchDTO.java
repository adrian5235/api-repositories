package com.adrian.api.dto;

public record BranchDTO(
        String name,
        String lastCommitSha
) {}
