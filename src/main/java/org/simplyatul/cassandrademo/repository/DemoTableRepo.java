package org.simplyatul.cassandrademo.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import org.simplyatul.cassandrademo.model.DemoTable;

@Repository
public interface DemoTableRepo
    extends CassandraRepository<DemoTable, String>, CustomizedSave<DemoTable> {

}

