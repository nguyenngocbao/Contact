package com.safetrust.interview.contact.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.safetrust.interview.contact.exception.ApplicationException;
import com.safetrust.interview.contact.models.dto.ContactDTO;



public interface ContactService {
	
	Page<ContactDTO> findAllContact(Pageable pageable);
	
	ContactDTO findContactById(Long id) throws ApplicationException;
	
	ContactDTO createOrUpdateContact( Long id,  ContactDTO contact);
	
	void deleteById(Long id);
	
	
}
