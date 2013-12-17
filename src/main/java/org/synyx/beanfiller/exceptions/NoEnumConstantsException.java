
package org.synyx.beanfiller.exceptions;

/**
 * Thrown if there are no constants declared in an Enum that is tried to be created.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class NoEnumConstantsException extends FillingException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates new NoEnumConstantsException with the given message.
     *
     * @param  message  the message.
     */
    public NoEnumConstantsException(String message) {

        super(message);
    }


    /**
     * Creates new NoEnumConstantsException with the given message and cause.
     *
     * @param  message  the message.
     * @param  cause  the throwable cause.
     */
    public NoEnumConstantsException(String message, Throwable cause) {

        super(message, cause);
    }
}
