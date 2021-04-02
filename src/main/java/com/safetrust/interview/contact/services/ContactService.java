package com.safetrust.interview.contact.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.safetrust.interview.contact.exception.ApplicationException;
import com.safetrust.interview.contact.models.dto.ContactDTO;

/**
 * 
 * Contact Service
 *
 */
public interface ContactService {
    /**
     * get all contacts
     * 
     * @param pageable: the pagination information
     * @return
     */
    Page<ContactDTO> findAllContact(Pageable pageable);

    /**
     * Get contact with id
     * 
     * @param id: of contact to be retrieved
     * @return
     * @throws ApplicationException
     */
    ContactDTO findContactById(Long id) throws ApplicationException;

    /**
     * Create or update contact
     * 
     * @param id:      of contact
     * @param contact: the contact to be persisted
     * @return
     */
    ContactDTO createOrUpdateContact(Long id, ContactDTO contact);

    /**
     * Delete the contact
     * 
     * @param id: of contact to be deleted
     */
    void deleteById(Long id);

}
