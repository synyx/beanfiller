
package org.synyx.beanfiller.exceptions;

/**
 * Exception for Errors on filling an Object.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class FillingException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Create new FillingException with the given message and cause.
     *
     * @param  message  the message.
     * @param  cause  the throwable cause.
     */
    public FillingException(String message, Throwable cause) {

        super(message, cause);
    }


    /**
     * Creates new FillingException with the given message.
     *
     * @param  message  the message.
     */
    public FillingException(String message) {

        super(message);
    }
}
