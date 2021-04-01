package com.safetrust.interview.contact.services.impl;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safetrust.interview.contact.exception.ApplicationException;
import com.safetrust.interview.contact.models.Contact;
import com.safetrust.interview.contact.models.dto.ContactDTO;
import com.safetrust.interview.contact.models.mapper.ContactMapper;
import com.safetrust.interview.contact.repositories.ContactRepository;
import com.safetrust.interview.contact.services.ContactService;


@Service
@Transactional
public class ContactServiceImpl implements ContactService{
    
    private ContactRepository contactRepo;
    
    private ContactMapper contactMapper;
    
    public ContactServiceImpl(ContactRepository contactRepo,ContactMapper contactMapper) {
        this.contactRepo = contactRepo;
        this.contactMapper = contactMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ContactDTO> findAllContact(Pageable pageable) {
        return contactRepo.findAll(pageable).map(contactMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ContactDTO findContactById(Long id) throws ApplicationException {
        Contact contact = contactRepo.findById(id).orElseThrow( () -> new ApplicationException(""));
        return  contactMapper.toDto(contact);
    }

    @Override
    public ContactDTO createOrUpdateContact(Long id, ContactDTO contactDTO) {
        Contact contact = contactMapper.toEntity(contactDTO);
        if ( Objects.nonNull(id) && contactRepo.findById( id ).isPresent() ) {
            contact.setId( id );
        } else {
            contact.setId( 0 ); // Set 0 here because we use DTO as creation request also. It is better to create requestVM type or use Json ignore here.
        }
       Contact contactReponse = contactRepo.save(contact);
       return contactMapper.toDto(contactReponse);
    }

    @Override
    public void deleteById(Long id) {
        contactRepo.deleteAllContactWithIds(Arrays.asList(id));
    }

   
	
}
