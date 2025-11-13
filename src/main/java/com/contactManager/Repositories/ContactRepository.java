package com.contactManager.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.contactManager.Entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer>{

	// pagination method
	
	@Query("from Contact as c where c.user.id=:id")
	Page<Contact> getAllContactsByUser(@Param("id") int id, Pageable pageable);
	// pageable object will have current page and number of records per page
	
	
}
