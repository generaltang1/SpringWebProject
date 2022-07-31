package com.thl.taengumall.repository;

import com.thl.taengumall.jpa.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Integer> {
    List<Record> findByAccountId(int id);
    Record findById(int id);
}