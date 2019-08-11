package org.simplyatul.cassandrademo.repository;

public interface CustomizedSave<T> {
    <S extends T> S save(S entity, Integer ttl, Integer cl);
}
