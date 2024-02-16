package com.adrian.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Repository {
    private String name;
    private Owner owner;
    private boolean fork = false;
    private List<Branch> branches;
}
