
package org.synyx.beanfiller.exceptions;

/**
 * Exception for Errors on filling an Object.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class FillingException extends Exception {

    private static final long serialVersionUID = 1L;

    public FillingException(String message, Throwable cause) {

        super(message, cause);
    }


    public FillingException(String message) {

        super(message);
    }
}
