package com.contactManager.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactManager.Entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
