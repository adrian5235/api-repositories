package com.adrian.api.mapper;

public interface Mapper<A, B> {
    B toDTO(A a);
}
