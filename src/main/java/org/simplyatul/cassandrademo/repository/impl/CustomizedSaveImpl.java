package org.simplyatul.cassandrademo.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.InsertOptions;

import org.simplyatul.cassandrademo.config.CassandraProperties;
import org.simplyatul.cassandrademo.repository.CustomizedSave;
import com.datastax.driver.core.ConsistencyLevel;

public class CustomizedSaveImpl<T> implements CustomizedSave<T> {

@Autowired
private CassandraOperations operations;

    @Autowired
    private CassandraProperties cassandraProp;

    @Override
    public <S extends T> S save(S entity, Integer ttl, Integer cl) {

        InsertOptions insertOptions =
                org.springframework.data.cassandra.core.InsertOptions.builder()
                .ttl(ttl)
                .consistencyLevel(
                        ConsistencyLevel.valueOf(cassandraProp.getCL(cl)))
                .build();

        operations.insert(entity, insertOptions);
        return entity;
    }
}

