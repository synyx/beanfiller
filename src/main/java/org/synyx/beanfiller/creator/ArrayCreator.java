package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.exceptions.FillingException;

import java.util.List;


/**
 * Creator interface for Arrays - implement this if you write your own Array Creators.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public interface ArrayCreator extends Creator {

    /**
     * Creates an Array of the given class and fills it with the given Objects.
     *
     * @param  objects  Objects to fill the Array with.
     * @param  arrayType  class of the array Objects
     *
     * @return  the created array Object.
     *
     * @throws  FillingException  if an error occurred.
     */
    Object createArray(List<Object> objects, Class arrayType) throws FillingException;


    /**
     * Get the size that is defined for the array.
     *
     * @return  int size
     */
    int getSize();
}
