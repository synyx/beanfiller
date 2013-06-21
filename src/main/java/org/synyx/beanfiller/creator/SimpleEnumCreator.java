
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.NoEnumConstantsException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class SimpleEnumCreator implements EnumCreator {

    @Override
    public Object createEnum(Class clazz) throws NoEnumConstantsException {

        Object[] enumArray = clazz.getEnumConstants();

        if (enumArray.length > 0) {
            List<Object> enumList = Arrays.asList(enumArray);

            Collections.shuffle(enumList);

            return enumList.get(0);
        }

        throw new NoEnumConstantsException("There are no constants defined in Enum class " + clazz.getName()
            + ". Please provide constants for this Enum!");
    }
}
