package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.CollectionCriteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Creator for Lists.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ListCreatorAbstract extends AbstractCollectionCreator<List<?>> {

    public ListCreatorAbstract(CollectionCriteria criteria) {

        super(criteria);
    }

    @Override
    protected Collection<Object> getImplementationOfCollectionsClass() {

        return new ArrayList<Object>();
    }
}
