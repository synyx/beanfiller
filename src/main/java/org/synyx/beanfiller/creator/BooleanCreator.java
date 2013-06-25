
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.services.RandomGenerator;


/**
 * Creator for Booleans.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class BooleanCreator implements SimpleCreator<Boolean> {

    @Override
    public Boolean create() {

        return RandomGenerator.getRandomBoolean();
    }
}
