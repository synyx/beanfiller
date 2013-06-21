
package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.services.RandomGenerator;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class BooleanBuilder implements Builder<Boolean> {

    @Override
    public Boolean build() {

        return RandomGenerator.getRandomBoolean();
    }
}
