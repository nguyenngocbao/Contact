package com.safetrust.interview.contact.integrationtest.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
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
    
    /**
     * Test case of get contact
     *
     * @throws IOException
     * @throws Exception
     */
    @Test
    public void getContactCase() throws IOException, Exception {
        // expected data
        ContactDTO expectedResult = new ContactDTO(1,"Nguyen Ngoc Bao","nngocbaostar@gmail.com","0342340787","Thu Duc District, HCM City");
        // compare
        MvcResult result = mockMvc.perform(get(CONTACT_URL.concat("/1"))).andExpect(status().is(200)).andReturn();
        assertEquals(asJsonString(expectedResult), result.getResponse().getContentAsString());
    }
    
    /**
     * test case for update
     * 
     * @throws Exception
     * @throws JsonProcessingException
     */
    @Test
    public void updateContactTestCase() throws JsonProcessingException, Exception {

        ContactRequest input = new ContactRequest((long) 1, "Nguyen Van B", "vanb@gmail.com", "0239863542",
                "Dictrict 4, HCM City");

        // compare
        MvcResult result = mockMvc.perform(
                put(CONTACT_URL.concat("/1")).contentType(MimeTypeUtils.APPLICATION_JSON_VALUE).content(asJsonString(input)))
                .andExpect(status().is(200)).andReturn();

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
    
    /**
     * Test case for delete menu
     *
     * @throws IOException
     * @throws Exception
     */
    @Test
    public void deleteContactTestCase() throws IOException, Exception {
        assertTrue(contactRepository.existsById((long) 2));
        MvcResult result = mockMvc.perform(delete(CONTACT_URL.concat("/2")).contentType(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200)).andReturn();
        assertEquals("", result.getResponse().getContentAsString());
        // check database
        assertFalse(contactRepository.existsById((long) 2));
    }
    
    

}
