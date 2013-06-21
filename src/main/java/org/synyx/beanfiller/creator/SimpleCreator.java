package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.exceptions.FillingException;


/**
 * Interface for Creators that create simple Java Objects - if you want to use your own Creator, in addition to
 * implementing this class, you also have to add it to the BeanFiller.
 *
 * <p>Note that there are some exceptions for implementing this class:<br/>
 * - For Enums, implement EnumCreator instead<br/>
 * - For Beans with Generics, implement GenericsCreator instead<br/>
 * - For Arrays, implement ArrayCreator instead</p>
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public interface SimpleCreator<T> extends Creator {

    /**
     * Creates an Object of class T.
     *
     * @return  the created Object
     *
     * @throws  FillingException  if an error occured.
     */
    T create() throws FillingException;
}
