package org.synyx.beanfiller.builder;

/**
 * Interface for all Builders - if you want to use your own builder, in addition to implementing this class, you also
 * have to add it to the BuilderMap of the BeanFiller.
 *
 * <p>Note that there are some exceptions for implementing this class:<br/>
 * - For Enums, extend EnumBuilder instead<br/>
 * - For Beans with Generics, extend GenericsBuilder instead<br/>
 * - For Arrays, extend ArrayBuilder instead</p>
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public interface Builder<T> {

    /**
     * Build an Object of class T.
     *
     * @return  the Built Object
     */
    T build();
}
