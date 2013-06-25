
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.ArrayCriteria;

import java.lang.reflect.Array;

import java.util.List;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class SimpleArrayCreator implements ArrayCreator {

    private final ArrayCriteria arrayCriteria;

    /**
     * Create a new SimpleArrayCreator with the default Criteria.
     */
    public SimpleArrayCreator() {

        this.arrayCriteria = new ArrayCriteria();
    }


    /**
     * Create a new SimpleArrayCreator with the given Criteria.
     *
     * @param  arrayCriteria
     */
    public SimpleArrayCreator(ArrayCriteria arrayCriteria) {

        this.arrayCriteria = arrayCriteria;
    }

    @Override
    public Object createArray(List<Object> objects, Class arrayType) {

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
    @Override
    public int getSize() {

        return arrayCriteria.getSize();
    }
}
