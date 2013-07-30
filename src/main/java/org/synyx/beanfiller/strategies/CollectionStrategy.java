
package org.synyx.beanfiller.strategies;

import org.synyx.beanfiller.creator.CollectionCreator;
import org.synyx.beanfiller.creator.Creator;
import org.synyx.beanfiller.domain.ObjectInformation;
import org.synyx.beanfiller.exceptions.FillingException;

import java.util.ArrayList;
import java.util.List;


/**
 * Strategy to handle java.util.Collection classes.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class CollectionStrategy extends AbstractCreatorStrategy {

    public CollectionStrategy() {

        super(PRIORITY_HIGH);
    }

    @Override
    public boolean canHandle(ObjectInformation objectInformation) {

        return isCollection(objectInformation.getClazz());
    }


    @Override
    public Object createObject(ObjectInformation objectInformation) throws FillingException {

        Creator creator = getCreator(objectInformation);

        if (creator != null) {
            checkCreatorHasRightClass(creator, CollectionCreator.class, objectInformation);

            CollectionCreator collectionCreator = (CollectionCreator) creator;

            // get the ObjectInformations from the TypeArguments of the map.

            List<ObjectInformation> typeArguments = getTypeArgumentObjectInformation(objectInformation);

            // get the values - because this is a map, the list should only contain the ObjectInformation for the values.
            List<Object> values = getValues(typeArguments.get(0), collectionCreator);

            return collectionCreator.createCollection(values);
        }

        return null;
    }


    /**
     * Create the values from the given ObjectInformation and CollectionCreator.
     *
     * @param  valueInformation
     * @param  collectionCreator
     *
     * @return
     *
     * @throws  CreationException
     */
    protected List<Object> getValues(ObjectInformation valueInformation, CollectionCreator collectionCreator)
        throws FillingException {

        List<Object> values = new ArrayList<Object>();

        for (int i = 0; i < collectionCreator.getSize(); i++) {
            Object typeObject = createObjectFromObjectInformation(valueInformation);
            values.add(typeObject);
        }

        return values;
    }
}
