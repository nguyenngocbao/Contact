package com.safetrust.interview.contact.models.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String emailAddress;

    @NotNull
    private String telephoneNumber;

    @NotNull
    private String postalAddress;
    
    
    

}
