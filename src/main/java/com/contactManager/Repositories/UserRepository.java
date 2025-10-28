package com.contactManager.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactManager.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
