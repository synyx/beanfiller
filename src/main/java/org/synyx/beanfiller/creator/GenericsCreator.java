package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.exceptions.FillingException;

import java.util.List;


/**
 * Creator class for Beans with Generics - implement this if you write your own Generics Creators.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public interface GenericsCreator<T> extends Creator {

    /**
     * Create an Object of Generic Type T and fills it with the given Objects - If there are multiple Generic Types,
     * they are in the given List the following order: 1-2-1-2 (mostly interesting for Collections and such).
     *
     * @param  genericObjects  Objects of the Generic Types of the Generics Object.
     *
     * @return  the created Object.
     *
     * @throws  FillingException  if an error occured.
     */
    T createWithGenerics(List<Object> genericObjects) throws FillingException;


    /**
     * Get the size defined for the Generics Object - mostly needed for collections such as List or Map. If
     *
     * @return
     */
    int getSize();
}
