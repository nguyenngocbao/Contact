package com.safetrust.interview.contact.models.mapper;

import org.mapstruct.Mapper;

import com.safetrust.interview.contact.models.Contact;
import com.safetrust.interview.contact.models.dto.ContactDTO;
@Mapper( componentModel = "spring" )
public interface ContactMapper extends EntityMapper<ContactDTO,Contact> {

}
