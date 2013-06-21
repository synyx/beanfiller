
package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Test;

import org.synyx.beanfiller.creator.EnumCreator;
import org.synyx.beanfiller.creator.SimpleCreator;
import org.synyx.beanfiller.creator.SimpleEnumCreator;
import org.synyx.beanfiller.testobjects.EnumsObject;
import org.synyx.beanfiller.testobjects.TestEnum;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Tests for Enums.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class EnumTest {

    private BeanFiller beanfiller = new BeanFiller();

    @Test
    public void testEnumIsFilled() throws FillingException {

        EnumsObject o = beanfiller.fillBean(new EnumsObject());

        Assert.assertNotNull("TestEnum was not filled!", o.getTestEnum());
    }


    @Test
    public void testErrorEnumIsNotFilled() throws FillingException {

        // The ErrorEnum has no Enum fields set and should therefore not be filled (because theres nothing to fill it with).
        EnumsObject o = beanfiller.fillBean(new EnumsObject());

        Assert.assertNull("ErrorEnum should not be filled!", o.getEmptyEnum());
    }


    @Test
    public void testAddedEnumCreatorIsUsed() throws FillingException {

        SimpleEnumCreator enumCreator = mock(SimpleEnumCreator.class);
        when(enumCreator.createEnum(TestEnum.class)).thenReturn(TestEnum.TEST1);
        beanfiller.addCreator(TestEnum.class, enumCreator);

        beanfiller.fillBean(new EnumsObject());

        // assert that our mock was called instead of the default EnumCreator
        verify(enumCreator).createEnum(TestEnum.class);
    }


    @Test
    public void testAddedSpecificEnumCreatorIsUsed() throws FillingException {

        EnumCreator enumCreator = mock(EnumCreator.class);
        when(enumCreator.createEnum(TestEnum.class)).thenReturn(TestEnum.TEST1);

        beanfiller.addCreatorForClassAndAttribute(EnumsObject.class, "testEnum", enumCreator);

        beanfiller.fillBean(new EnumsObject());

        // assert that our mock was called instead of the default EnumCreator
        verify(enumCreator).createEnum(TestEnum.class);
    }


    @Test(expected = WrongCreatorException.class)
    public void testWrongCreatorExceptionIsThrownIfNonEnumCreatorIsUsed() throws FillingException {

        SimpleCreator stringCreator = new org.synyx.beanfiller.creator.StringCreator();

        beanfiller.addCreatorForClassAndAttribute(EnumsObject.class, "testEnum", stringCreator);

        beanfiller.fillBean(new EnumsObject());
    }
}
