
package org.synyx.beanfiller.exceptions;

/**
 * Exception that gets thrown if another Creator is set for a class or a field as the BeanFiller expects (eg. a non
 * GenericsCreator for an Object with generic types).
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class WrongCreatorException extends FillingException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates new WrongCreatorException with the given message.
     *
     * @param  message  the message.
     */
    public WrongCreatorException(String message) {

        super(message);
    }
}
