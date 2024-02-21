package org.synyx.beanfiller.strategies;

import org.junit.Test;
import org.synyx.beanfiller.BeanFiller;
import org.synyx.beanfiller.domain.ObjectInformation;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.services.CreatorRegistry;
import org.synyx.beanfiller.testobjects.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.synyx.beanfiller.testobjects.CopyConstructorObjectWithPrivateConstructor.COPY_CONSTRUCTOR_USED_VALUE;


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
    public void testTakesTheConstructorWithTheHighestNumberOfParameters() throws FillingException {

        JustAnotherBeanStrategy strategy = setupStrategy();

        MultipleConstructorsObject object = (MultipleConstructorsObject) strategy.createObject(new ObjectInformation(
                    MultipleConstructorsObject.class, null, null, null, null, null));
        assertThat(object, notNullValue());
        assertThat(object.getFoo(), notNullValue());
        assertThat(object.getBar(), notNullValue());
    }

    @Test(expected = FillingException.class)
    public void failsWhenOnlyCopyConstructorIsAvailable() throws FillingException {

        JustAnotherBeanStrategy strategy = setupStrategy();

        strategy.createObject(new ObjectInformation(
                CopyConstructorObject.class, null, null, null, null, null));
    }

    @Test
    public void usesPrivateConstructorOverCopyConstructor() throws FillingException {

        assertThat(new CopyConstructorObjectWithPrivateConstructor(null).getValue(), is(COPY_CONSTRUCTOR_USED_VALUE));

        JustAnotherBeanStrategy strategy = setupStrategy();

        CopyConstructorObjectWithPrivateConstructor object = (CopyConstructorObjectWithPrivateConstructor)
                strategy.createObject(new ObjectInformation(CopyConstructorObjectWithPrivateConstructor.class, null, null, null, null, null));
        assertThat(object, notNullValue());

        assertThat(object.getValue(), is(not(COPY_CONSTRUCTOR_USED_VALUE)));
    }


    private JustAnotherBeanStrategy setupStrategy() {

        JustAnotherBeanStrategy strategy = new JustAnotherBeanStrategy();
        CreatorRegistry registry = new CreatorRegistry(new BeanFiller().getCreatorMap());
        StrategyManager manager = new StrategyManager(registry);
        strategy.setStrategyManager(manager);

        return strategy;
    }
}
