package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.CollectionCriteria;

import java.util.Collection;
import java.util.List;


/**
 * Abstract parent for classes that implement java.util.Collection.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public abstract class AbstractCollectionCreator<T extends Collection> implements Creator {

    private final CollectionCriteria criteria;

    protected AbstractCollectionCreator(CollectionCriteria criteria) {

        this.criteria = criteria;
    }

    /**
     * Creates an Collection and fills it with the given Objects.
     *
     * @param  values  values for the Collection.
     *
     * @return  the created Object.
     */
    public <T> Collection<T> createCollection(List<T> values) {

        Collection collection = getImplementationOfCollectionsClass();

        for (T value : values) {
            collection.add(value);
        }

        return collection;
    }


    /**
     * Returns the size, the collection should have.
     *
     * @return  expected size of collection
     */
    public int getSize() {

        return criteria.getSize();
    }


    /**
     * Returns an instance of an Implementation of the needed collection class.
     *
     * @return  Instance of a Collection Implementation
     */
    protected abstract Collection<?> getImplementationOfCollectionsClass();
}
