package com.example.demo.respository;

import com.example.demo.entity.AccountEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<AccountEntity, Integer> {

  List<AccountEntity> findAllByAccountNumber(Integer accountNumber);

}
