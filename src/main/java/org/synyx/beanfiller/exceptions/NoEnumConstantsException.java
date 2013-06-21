
package org.synyx.beanfiller.exceptions;

/**
 * Thrown if there are no constants declared in an Enum that is tried to be created.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class NoEnumConstantsException extends FillingException {

    private static final long serialVersionUID = 1L;

    public NoEnumConstantsException(String message) {

        super(message);
    }


    public NoEnumConstantsException(String message, Exception ex) {

        super(message, ex);
    }
}
