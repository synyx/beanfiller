
package org.synyx.beanfiller.strategies;

import org.synyx.beanfiller.creator.AbstractCollectionCreator;
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
            checkCreatorHasRightClass(creator, AbstractCollectionCreator.class, objectInformation);

            AbstractCollectionCreator abstractCollectionCreator = (AbstractCollectionCreator) creator;

            // get the ObjectInformations from the TypeArguments of the map.

            List<ObjectInformation> typeArguments = getTypeArgumentObjectInformation(objectInformation);

            // get the values - because this is a map, the list should only contain
            // the ObjectInformation for the values.
            List<Object> values = getValues(typeArguments.get(0), abstractCollectionCreator);

            return abstractCollectionCreator.createCollection(values);
        }

        return null;
    }


    /**
     * Create the values from the given ObjectInformation and AbstractCollectionCreator.
     *
     * @param  valueInformation
     * @param  abstractCollectionCreator
     *
     * @return
     *
     * @throws  FillingException
     */
    protected List<Object> getValues(ObjectInformation valueInformation,
        AbstractCollectionCreator abstractCollectionCreator) throws FillingException {

        List<Object> values = new ArrayList<Object>();

        for (int i = 0; i < abstractCollectionCreator.getSize(); i++) {
            Object typeObject = createObjectFromObjectInformation(valueInformation);
            values.add(typeObject);
        }

        return values;
    }
}
