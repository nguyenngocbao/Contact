package com.safetrust.interview.contact.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetrust.interview.contact.exception.ApplicationException;
import com.safetrust.interview.contact.models.dto.ContactDTO;
import com.safetrust.interview.contact.services.ContactService;
import com.safetrust.interview.contact.util.ResponseUtil;

import io.swagger.annotations.ApiOperation;

/**
 * Controller handles requests to create/delete/update/get contacts
 * 
 */
@RestController
@RequestMapping(ContactControler.CONTACT_MAPPING)
public class ContactControler {

    public static final String CONTACT_MAPPING = "/contact";
    private static final Logger logger = LoggerFactory.getLogger(ContactControler.class);

    private ContactService contactService;

    public ContactControler(ContactService contactService) {
        this.contactService = contactService;
    }

    /**
     * GET /contact/:id : Get the "id" contact.
     *
     * @param id: the id of the contactDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contactDTO,
     *         or with status 404 (Not Found)
     * @throws ApplicationException
     */
    @ApiOperation(value = "Get the contact with id", response = ResponseEntity.class)
    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getContact(@PathVariable Long id) throws ApplicationException {
        logger.debug("REST request to get contact : {}", id);

        ContactDTO contactDTO = contactService.findContactById(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(contactDTO));
    }

    /**
     * GET /contact : Get all the contacts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of contact in
     *         body
     */
    @ApiOperation(value = "Get all the contacts", response = ResponseEntity.class)
    @GetMapping
    public ResponseEntity<Page<ContactDTO>> getAllContacts(Pageable pageable) throws ApplicationException {
        logger.debug("REST request to get a page of contacts");
        Page<ContactDTO> page = contactService.findAllContact(pageable);
        return new ResponseEntity<>(page, null, HttpStatus.OK);
    }

    /**
     * POST /contact: Create contact
     * 
     * @param : contain data about the contact to be created
     * @return the ResponseEntity with status 200 (OK)
     * @throws URISyntaxException
     */
    @ApiOperation(value = "Create a new contact", response = ResponseEntity.class)
    @PostMapping
    public ResponseEntity<ContactDTO> createContact(@RequestBody @Valid ContactDTO contactDTO)
            throws URISyntaxException {

        ContactDTO result = contactService.createOrUpdateContact(null, contactDTO);
        return ResponseEntity.created(new URI(CONTACT_MAPPING + "/" + result.getId())).body(result);
    }

    /**
     * PUT /contact: Updates an existing contact
     * 
     * @param : the clientDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     *         clientDTO,
     * @throws URISyntaxException
     */
    @ApiOperation(value = "Updates an existing contact", response = ResponseEntity.class)
    @PutMapping("/{id}")
    public ResponseEntity<ContactDTO> updateContact(@PathVariable Long id, @RequestBody @Valid ContactDTO contactDTO)
            throws URISyntaxException {

        ContactDTO result = contactService.createOrUpdateContact(id, contactDTO);
        return ResponseEntity.ok().body(result);
    }

    /**
     * DELETE /contact/:id : Delete the "id" contact.
     *
     * @param id: the id of the contact to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @ApiOperation(value = "Delete a contact with id", response = ResponseEntity.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        logger.debug("REST request to delete contact : {}", id);
        contactService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
