package org.synyx.beanfiller.builder;

import java.util.List;


/**
 * Builder class for Beans with Generics - Extend this if you write your own Generics Builders.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public abstract class GenericsBuilder<T> implements Builder<T> {

    /**
     * Build an Object of Generic Type T and fills it with the given Objects (Of the Generic Type) - If there are
     * multiple Generic Types, they are in the following order: 1-2-1-2.
     *
     * @param  genericObjects  Objects of the Generic Types of the Generics Object.
     *
     * @return  the built Object.
     */
    public abstract T buildWithGenerics(List<Object> genericObjects);


    public abstract int getSize();


    /**
     * Returns null, because it does not work for Generics, we need more information here.
     *
     * @return  null
     */
    @Override
    public T build() {

        return null;
    }
}
