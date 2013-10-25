package org.synyx.beanfiller.strategies;

import org.synyx.beanfiller.creator.Creator;
import org.synyx.beanfiller.creator.EnumCreator;
import org.synyx.beanfiller.domain.ObjectInformation;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.exceptions.WrongCreatorException;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class EnumStrategy extends AbstractCreatorStrategy {

    public EnumStrategy() {

        super(PRIORITY_HIGH);
    }

    @Override
    public boolean canHandle(ObjectInformation objectInformation) {

        return isEnum(objectInformation.getClazz());
    }


    @Override
    public Object createObjectInternal(ObjectInformation objectInformation) throws FillingException {

        Creator creator = getEnumCreator(objectInformation);

        if (creator != null) {
            checkCreatorHasRightClass(creator, EnumCreator.class, objectInformation);

            if (!EnumCreator.class.isAssignableFrom(creator.getClass())) {
                throw new WrongCreatorException("The Creator got for class " + objectInformation.getClazz().getName()
                    + " and field " + objectInformation.getField().getName()
                    + " does not extend " + EnumCreator.class.getName());
            }

            EnumCreator enumCreator = (EnumCreator) creator;

            return enumCreator.createEnum(objectInformation.getClazz());
        }

        return null;
    }


    private Creator getEnumCreator(ObjectInformation objectInformation) {

        Creator creator = getCreator(objectInformation);

        if (creator == null) {
            creator = getCreatorRegistry().getCreatorForClass(Enum.class);
        }

        return creator;
    }
}
