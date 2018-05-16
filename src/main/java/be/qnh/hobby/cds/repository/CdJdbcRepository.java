package be.qnh.hobby.cds.repository;

import be.qnh.hobby.cds.domain.CompactDisc;

import java.util.List;

public interface CdJdbcRepository {
    List<CompactDisc> findAll();
}
