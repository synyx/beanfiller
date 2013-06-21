package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.FillingException;


/**
 * Creator interface for Enums - implement this if you write your own Enum Creators.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public interface EnumCreator extends Creator {

    /**
     * create the enum of the given class.
     *
     * @param  clazz
     *
     * @return  the created Enum or null, if no EnumConstants are defined in the Enum class.
     *
     * @throws  FillingException  if an error occured.
     */
    Object createEnum(Class clazz) throws FillingException;
}
