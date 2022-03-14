package com.example.demo.respository;

import com.example.demo.entity.BankUser;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<BankUser, Integer> {

  Optional<BankUser> findByUserName(String name);

}
