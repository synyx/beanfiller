package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Test;

import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.testobjects.cycling.ArrayCycleObject;
import org.synyx.beanfiller.testobjects.cycling.ArrayCycleObject2;
import org.synyx.beanfiller.testobjects.cycling.ArrayCycleObject3;
import org.synyx.beanfiller.testobjects.cycling.ArrayCycleObject4;
import org.synyx.beanfiller.testobjects.cycling.CyclicObject;
import org.synyx.beanfiller.testobjects.cycling.CyclicObject2;
import org.synyx.beanfiller.testobjects.cycling.ListCycleObject;
import org.synyx.beanfiller.testobjects.cycling.MapCycleObject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


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

        assertNull("The cycling Object should not be filled!", object.getCycle());
    }


    @Test
    public void testDeeperBeanCycle() throws FillingException {

        CyclicObject2 object = beanfiller.fillBean(CyclicObject2.class);

        assertNotNull("No cycle yet, this should be filled!", object.getCyclicObject3());

        assertNotNull("No cycle yet, this should be filled!", object.getCyclicObject3().getCyclicObject4());

        assertNull("The cycling Object should not be filled!",
            object.getCyclicObject3().getCyclicObject4().getCyclicObject2());
    }


    @Test
    public void testCyclesWithArrays() throws FillingException {

        ArrayCycleObject arrayCycleObject = beanfiller.fillBean(ArrayCycleObject.class);

        CyclicObject2[] cyclicObject2Array = arrayCycleObject.getCyclicObject2array();
        assertNotNull("No cycle yet, this should be filled!", cyclicObject2Array);

        for (CyclicObject2 object : cyclicObject2Array) {
            assertNotNull("No cycle yet, this should be filled!", object);
            assertNotNull("No cycle yet, this should be filled!", object.getCyclicObject3());

            assertNotNull("No cycle yet, this should be filled!", object.getCyclicObject3().getCyclicObject4());

            assertNull("The cycling Object should not be filled!",
                object.getCyclicObject3().getCyclicObject4().getCyclicObject2());
        }
    }


    @Test
    public void testCyclesWithOnlyArrays() throws FillingException {

        ArrayCycleObject4 arrayCycleObject4 = beanfiller.fillBean(ArrayCycleObject4.class);

        ArrayCycleObject2[] array = arrayCycleObject4.getArrayCycleObject2Array();
        assertNotNull("No cycle yet, the array on arrayCycleObject4 should be filled!", array);

        for (ArrayCycleObject2 arrayCycleObject2 : array) {
            assertNotNull("No cycle yet, arrayCycleObject2 should be filled!", arrayCycleObject2);

            assertNotNull("No cycle yet, the array on arrayCycleObject2 should be filled!",
                arrayCycleObject2.getArrayCycleObject3Array());

            for (ArrayCycleObject3 arrayCycleObject3 : arrayCycleObject2.getArrayCycleObject3Array()) {
                assertNotNull("No cycle yet, arrayCycleObject3 should be filled!", arrayCycleObject3);
                assertNull("The Array on arrayCycleObject3 should not be filled as it is introducing a cycle!",
                    arrayCycleObject3.getArrayCycleObject3Array());
            }
        }
    }


    @Test
    public void testCyclesWithMap() throws FillingException {

        MapCycleObject mapCycleObject = beanfiller.fillBean(MapCycleObject.class);

        assertNull("The map should not be filled, because it is introducing a cycle!", mapCycleObject.getMap());
    }


    @Test
    public void testCyclesWithList() throws FillingException {

        ListCycleObject listCycleObject = beanfiller.fillBean(ListCycleObject.class);

        assertNull("The list should not be filled, because it is introducing a cycle!",
            listCycleObject.getList());
    }
}
