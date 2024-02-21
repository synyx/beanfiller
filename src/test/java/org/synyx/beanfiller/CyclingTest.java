package org.synyx.beanfiller;

import org.junit.jupiter.api.Test;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.testobjects.cycling.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;


/**
 * TestCases for testing the handling of cyclic Dependencies within Objects.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class CyclingTest {

    private final BeanFiller beanfiller = new BeanFiller();

    @Test
    public void testBeanCycle() throws FillingException {

        CyclicObject object = beanfiller.fillBean(CyclicObject.class);

        assertThat(object.getCycle(), nullValue());
    }


    @Test
    public void testDeeperBeanCycle() throws FillingException {

        CyclicObject2 object = beanfiller.fillBean(CyclicObject2.class);

        assertThat(object.getCyclicObject3(), notNullValue());
        assertThat(object.getCyclicObject3().getCyclicObject4(), notNullValue());

        assertThat(object.getCyclicObject3().getCyclicObject4().getCyclicObject2(), nullValue());
    }


    @Test
    public void testCyclesWithArrays() throws FillingException {

        ArrayCycleObject arrayCycleObject = beanfiller.fillBean(ArrayCycleObject.class);

        CyclicObject2[] cyclicObject2Array = arrayCycleObject.getCyclicObject2array();
        assertThat(cyclicObject2Array, notNullValue());

        for (CyclicObject2 object : cyclicObject2Array) {
            assertThat(object, notNullValue());
            assertThat(object.getCyclicObject3(), notNullValue());
            assertThat(object.getCyclicObject3().getCyclicObject4(), notNullValue());

            assertThat(object.getCyclicObject3().getCyclicObject4().getCyclicObject2(), nullValue());
        }
    }


    @Test
    public void testCyclesWithOnlyArrays() throws FillingException {

        ArrayCycleObject4 arrayCycleObject4 = beanfiller.fillBean(ArrayCycleObject4.class);

        ArrayCycleObject2[] array = arrayCycleObject4.getArrayCycleObject2Array();
        assertThat(array, notNullValue());

        for (ArrayCycleObject2 arrayCycleObject2 : array) {
            assertThat(arrayCycleObject2, notNullValue());
            assertThat(arrayCycleObject2.getArrayCycleObject3Array(), notNullValue());

            for (ArrayCycleObject3 arrayCycleObject3 : arrayCycleObject2.getArrayCycleObject3Array()) {
                assertThat(arrayCycleObject3, notNullValue());
                assertThat(arrayCycleObject3.getArrayCycleObject3Array(), nullValue());
            }
        }
    }


    @Test
    public void testCyclesWithMap() throws FillingException {

        MapCycleObject mapCycleObject = beanfiller.fillBean(MapCycleObject.class);
        assertThat(mapCycleObject.getMap(), nullValue());
    }


    @Test
    public void testCyclesWithList() throws FillingException {

        ListCycleObject listCycleObject = beanfiller.fillBean(ListCycleObject.class);
        assertThat(listCycleObject.getList(), nullValue());
    }
}
