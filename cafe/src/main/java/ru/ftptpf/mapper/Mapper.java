package ru.ftptpf.mapper;

public interface Mapper<F, T> {
    T map(F from);
}
