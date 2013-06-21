
package org.synyx.beanfiller.creator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class SimpleEnumCreator implements EnumCreator {

    private static final Logger LOG = LoggerFactory.getLogger(EnumCreator.class);

    @Override
    public Object createEnum(Class clazz) {

        Object[] enumArray = clazz.getEnumConstants();

        if (enumArray.length > 0) {
            List<Object> enumList = Arrays.asList(enumArray);

            Collections.shuffle(enumList);

            return enumList.get(0);
        }

        LOG.warn("No EnumConstants defined in Enum class: " + clazz.getName());

        return null;
    }
}
