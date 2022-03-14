package com.example.demo.respository;

import com.example.demo.entity.TransactionsEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionsRepository extends CrudRepository<TransactionsEntity, Integer> {

}
