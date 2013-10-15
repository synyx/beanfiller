package org.synyx.beanfiller.strategies;

import org.synyx.beanfiller.creator.Creator;
import org.synyx.beanfiller.creator.SimpleCreator;
import org.synyx.beanfiller.domain.ObjectInformation;
import org.synyx.beanfiller.exceptions.FillingException;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class SimpleObjectStrategy extends AbstractCreatorStrategy {

    public SimpleObjectStrategy() {

        super(PRIORITY_LOW);
    }

    @Override
    public boolean canHandle(ObjectInformation objectInformation) {

        // if we find a creator for the object, we can handle it.
        return getCreator(objectInformation) != null;
    }


    @Override
    public Object createObject(ObjectInformation objectInformation) throws FillingException {

        Creator creator = getCreator(objectInformation);
        checkCreatorHasRightClass(creator, SimpleCreator.class, objectInformation);

        SimpleCreator simpleCreator = (SimpleCreator) creator;

        return simpleCreator.create();
    }
}
