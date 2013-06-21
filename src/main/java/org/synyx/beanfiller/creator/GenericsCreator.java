package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.FillingException;

import java.util.List;


/**
 * Creator class for Beans with Generics - implement this if you write your own Generics Creators.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public interface GenericsCreator<T> extends Creator {

    /**
     * Create an Object of Generic Type T and fills it with the given Objects (Of the Generic Type) - If there are
     * multiple Generic Types, they are in the following order: 1-2-1-2.
     *
     * @param  genericObjects  Objects of the Generic Types of the Generics Object.
     *
     * @return  the created Object.
     *
     * @throws  FillingException  if an error occured.
     */
    T createWithGenerics(List<Object> genericObjects) throws FillingException;


    int getSize();
}
