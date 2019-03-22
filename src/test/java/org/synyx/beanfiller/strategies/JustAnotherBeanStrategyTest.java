package org.synyx.beanfiller.strategies;

import org.junit.Test;

import org.synyx.beanfiller.BeanFiller;
import org.synyx.beanfiller.domain.ObjectInformation;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.services.CreatorRegistry;
import org.synyx.beanfiller.testobjects.MultipleConstructorsObject;
import org.synyx.beanfiller.testobjects.NoDefaultConstructorObject;
import org.synyx.beanfiller.testobjects.PrivateConstructorObject;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import static org.junit.Assert.assertThat;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class JustAnotherBeanStrategyTest {

    @Test
    public void testPrivateConstructor() throws FillingException {

        JustAnotherBeanStrategy strategy = setupStrategy();
        PrivateConstructorObject object = (PrivateConstructorObject) strategy.createObject(new ObjectInformation(
                    PrivateConstructorObject.class, null, null, null, null, null));
        assertThat(object, notNullValue());
    }


    @Test
    public void testNoDefaultConstructor() throws FillingException {

        JustAnotherBeanStrategy strategy = setupStrategy();

        NoDefaultConstructorObject object = (NoDefaultConstructorObject) strategy.createObject(new ObjectInformation(
                    NoDefaultConstructorObject.class, null, null, null, null, null));
        assertThat(object, notNullValue());
        assertThat(object.getFoo(), notNullValue());
    }


    @Test
    public void testTakesTheConstructorWithTheLeastNumberOfParameters() throws FillingException {

        JustAnotherBeanStrategy strategy = setupStrategy();

        MultipleConstructorsObject object = (MultipleConstructorsObject) strategy.createObject(new ObjectInformation(
                    MultipleConstructorsObject.class, null, null, null, null, null));
        assertThat(object, notNullValue());
        assertThat(object.getFoo(), notNullValue());
        assertThat(object.getBar(), nullValue());
    }


    private JustAnotherBeanStrategy setupStrategy() {

        JustAnotherBeanStrategy strategy = new JustAnotherBeanStrategy();
        CreatorRegistry registry = new CreatorRegistry(new BeanFiller().getCreatorMap());
        StrategyManager manager = new StrategyManager(registry);
        strategy.setStrategyManager(manager);

        return strategy;
    }
}
