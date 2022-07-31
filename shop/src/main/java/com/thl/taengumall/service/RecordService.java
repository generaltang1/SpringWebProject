package com.thl.taengumall.service;

import com.thl.taengumall.jpa.Account;
import com.thl.taengumall.jpa.Product;
import com.thl.taengumall.jpa.Record;
import com.thl.taengumall.repository.AccountRepository;
import com.thl.taengumall.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordService {
    @NotNull
    private final RecordRepository repository;

    public List<Record> getRecords() {
        var records = repository.findAll(Sort.by(Sort.Direction.DESC, "created"));
        return records;
    }

    public List<Record> getRecordsByAccountId(int id) {
        var records = repository.findByAccountId(id);
        return records;
    }

    public Record getRecordById(int id) {
        var record = repository.findById(id);
        return record;
    }

    public void updateRecord(Record record) {
        var p = repository.findById(record.getId());
        p.setState(record.getState());
    }

    public void addRecord(Account user, Product product, int count) {
        var record = new Record();

        record.setCount(count);
        record.setState(0);
        record.setProductId(product.getId());
        record.setAccountId(user.getId());

        repository.save(record);
    }
}
