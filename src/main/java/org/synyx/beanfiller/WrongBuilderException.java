
package org.synyx.beanfiller;

/**
 * Exception that gets thrown if another Builder is set for a class or a field as the BeanFiller expects (eg. a non
 * GenericsBuilder for an Object with generic types).
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class WrongBuilderException extends FillingException {

    private static final long serialVersionUID = 1L;

    public WrongBuilderException(String message) {

        super(message);
    }


    public WrongBuilderException(String message, Exception ex) {

        super(message, ex);
    }
}
