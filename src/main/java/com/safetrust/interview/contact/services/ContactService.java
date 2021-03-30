package com.safetrust.interview.contact.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.safetrust.interview.contact.models.Contact;

public interface ContactService {
	
	List<Contact> findAll(Pageable pageable);
	
	Optional<Contact> findOne(Long id);
	
	
	


}
