package com.safetrust.interview.contact.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * This class used to wrap the exceptions to be sent back to the client
 * 
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageInfo {
    
    private String message;

}
