package com.safetrust.interview.contact.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.safetrust.interview.contact.models.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>  {

}
