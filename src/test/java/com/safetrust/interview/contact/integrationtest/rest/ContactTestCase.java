package com.safetrust.interview.contact.integrationtest.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.MimeTypeUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.safetrust.interview.contact.integrationtest.BaseTestCase;
import com.safetrust.interview.contact.model.ContactRequest;
import com.safetrust.interview.contact.models.Contact;
import com.safetrust.interview.contact.models.dto.ContactDTO;
import com.safetrust.interview.contact.repositories.ContactRepository;

public class ContactTestCase extends BaseTestCase {

    private static final String CONTACT_URL = "/contact";

    @Autowired
    private ContactRepository contactRepository;

    /**
     * test case for create
     * 
     * @throws Exception
     * @throws JsonProcessingException
     */
    @Test
    public void createContactTestCase() throws JsonProcessingException, Exception {

        ContactRequest input = new ContactRequest((long) 0, "Nguyen Van A", "abx@gmail.com", "0239863542",
                "Dictrict 5, HCM City");

        // compare
        MvcResult result = mockMvc.perform(
                post(CONTACT_URL).contentType(MimeTypeUtils.APPLICATION_JSON_VALUE).content(asJsonString(input)))
                .andExpect(status().is(201)).andReturn();

        ContactDTO resultData = jsonToObject(result.getResponse().getContentAsString(), ContactDTO.class);
        assertEquals(input.getName(), resultData.getName());
        assertEquals(input.getEmailAddress(), resultData.getEmailAddress());
        assertEquals(input.getTelephoneNumber(), resultData.getTelephoneNumber());
        assertEquals(input.getPostalAddress(), resultData.getPostalAddress());
        // check database
        Optional<Contact> contact = contactRepository.findById(resultData.getId());
        assertTrue(contact.isPresent());
        assertEquals(input.getName(), contact.get().getName());
        assertEquals(input.getEmailAddress(), contact.get().getEmailAddress());
        assertEquals(input.getTelephoneNumber(), contact.get().getTelephoneNumber());
        assertEquals(input.getPostalAddress(), contact.get().getPostalAddress());

    }
    
    

}
