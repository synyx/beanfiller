package org.synyx.beanfiller.strategies;

import org.junit.Test;

import org.synyx.beanfiller.domain.ObjectInformation;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.testobjects.PrivateConstructorObject;

import static org.hamcrest.Matchers.notNullValue;

import static org.junit.Assert.assertThat;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class JustAnotherBeanStrategyTest {

    @Test
    public void testPrivateConstructor() throws FillingException {

        PrivateConstructorObject object = (PrivateConstructorObject)
            new JustAnotherBeanStrategy().createObject(new ObjectInformation(PrivateConstructorObject.class, null,
                    null, null, null, null));
        assertThat(object, notNullValue());
    }
}
