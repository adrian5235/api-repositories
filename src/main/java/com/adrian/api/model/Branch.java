package com.adrian.api.model;

import lombok.Builder;

@Builder
public record Branch (
        String name,
        Commit commit
) {}
