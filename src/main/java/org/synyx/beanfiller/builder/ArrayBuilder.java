package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.criteria.ArrayCriteria;

import java.lang.reflect.Array;

import java.util.List;


/**
 * Builder class for Arrays - Extend this if you write your own Array Builders.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ArrayBuilder implements Builder<Object> {

    private ArrayCriteria arrayCriteria;

    /**
     * Create the ArrayBuilder with the default Criteria.
     */
    public ArrayBuilder() {

        this.arrayCriteria = new ArrayCriteria();
    }


    /**
     * Create the ArrayBuilder with the given Criteria.
     *
     * @param  arrayCriteria
     */
    public ArrayBuilder(ArrayCriteria arrayCriteria) {

        this.arrayCriteria = arrayCriteria;
    }

    /**
     * Returns null, because it does not work for Arrays.
     *
     * @return
     */
    @Override
    public Object build() {

        return null;
    }


    /**
     * Build the Array.
     *
     * @param  objects  Objects to fill the Array with.
     * @param  arrayType  class of the array Objects
     *
     * @return  the built array Object.
     */
    public Object buildArray(List<Object> objects, Class arrayType) {

        // create a new instance of the array with the given type
        Object array = Array.newInstance(arrayType, getSize());

        // fill the array
        for (int i = 0; i < getSize(); i++) {
            Array.set(array, i, objects.get(i));
        }

        return array;
    }


    /**
     * Get the size that is defined for the array.
     *
     * @return  int size
     */
    public int getSize() {

        return arrayCriteria.getSize();
    }
}
