package com.safetrust.interview.contact.services.impl;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.safetrust.interview.contact.exception.ApplicationException;
import com.safetrust.interview.contact.models.Contact;
import com.safetrust.interview.contact.models.dto.ContactDTO;
import com.safetrust.interview.contact.models.mapper.ContactMapper;
import com.safetrust.interview.contact.repositories.ContactRepository;
import com.safetrust.interview.contact.services.ContactService;

/**
 * 
 * The contact service implementation
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepo;

    private ContactMapper contactMapper;

    public ContactServiceImpl(ContactRepository contactRepo, ContactMapper contactMapper) {
        this.contactRepo = contactRepo;
        this.contactMapper = contactMapper;
    }

    /**
     * get all contacts
     * 
     * @param pageable: the pagination information
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ContactDTO> findAllContact(Pageable pageable) {
        return contactRepo.findAll(pageable).map(contactMapper::toDto);
    }

    /**
     * Get contact with id
     * 
     * @param id: of contact to be retrieved
     * @return
     * @throws ApplicationException
     */
    @Override
    @Transactional(readOnly = true)
    public ContactDTO findContactById(Long id) throws ApplicationException {
        Contact contact = contactRepo.findById(id).orElseThrow(() 
                -> new ApplicationException(String.format("Contact with ID [%s] not found", id)));
        return contactMapper.toDto(contact);
    }

    /**
     * Create or update contact
     * 
     * @param id:      of contact
     * @param contact: the contact to be persisted
     * @return
     */
    @Override
    public ContactDTO createOrUpdateContact(Long id, ContactDTO contactDTO) {
        Contact contact = contactMapper.toEntity(contactDTO);
        if (Objects.nonNull(id) && contactRepo.findById(id).isPresent()) {
            contact.setId(id);
        } else {
            contact.setId((Long)null);
        }
        Contact contactReponse = contactRepo.save(contact);
        return contactMapper.toDto(contactReponse);
    }

    /**
     * Delete the contact
     * 
     * @param id: of contact to be deleted
     */
    @Override
    public void deleteById(Long id) {
        contactRepo.deleteAllContactWithIds(Arrays.asList(id));
    }

}
