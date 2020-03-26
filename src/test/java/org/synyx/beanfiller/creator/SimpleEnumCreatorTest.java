package org.synyx.beanfiller.creator;

import org.junit.Assert;
import org.junit.Test;

import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.exceptions.NoEnumConstantsException;
import org.synyx.beanfiller.testobjects.EmptyEnum;
import org.synyx.beanfiller.testobjects.TestEnum;

import static org.junit.Assert.assertNotNull;


/**
 * Tests for the SimpleEnumCreator.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class SimpleEnumCreatorTest {

    @Test
    public void testEnumCreatorSelectsEnum() throws FillingException {

        EnumCreator enumCreator = new SimpleEnumCreator();
        TestEnum testEnum = (TestEnum) enumCreator.createEnum(TestEnum.class);

        // assert that a TestEnum was created
        assertNotNull("TestEnum is null!", testEnum);
    }


    @Test(expected = NoEnumConstantsException.class)
    public void testEnumCreatorThrowsExceptionIfNoEnumConstants() throws FillingException {

        EnumCreator enumCreator = new SimpleEnumCreator();

        // the EmptyEnum does not have Enum Constants defined, so an Exception should be thrown.
        enumCreator.createEnum(EmptyEnum.class);
    }
}
