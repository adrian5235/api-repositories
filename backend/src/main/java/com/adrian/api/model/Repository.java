package com.adrian.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class Repository {
    private String name;
    private Owner owner;
    private boolean fork;
    private List<Branch> branches;
}
