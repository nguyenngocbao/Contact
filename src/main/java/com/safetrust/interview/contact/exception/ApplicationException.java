package com.safetrust.interview.contact.exception;
/**
 * Application application exception
 */
public class ApplicationException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * 
     * @param message
     */
    public ApplicationException( String message ) {
        super( message );
    }

    /**
     * Constructor
     * 
     * @param message
     * @param exception
     */
    public ApplicationException( String message, Exception exception ) {
        super( message, exception );
    }

}
