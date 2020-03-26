
package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Test;

import org.synyx.beanfiller.creator.EnumCreator;
import org.synyx.beanfiller.creator.SimpleEnumCreator;
import org.synyx.beanfiller.creator.StringCreator;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.exceptions.WrongCreatorException;
import org.synyx.beanfiller.testobjects.BaseObject;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Tests for the basic types.
 *
 * @author Tobias Knell - knell@synyx.de
 */
public class BasicTest {

    private final BeanFiller beanfiller = new BeanFiller();
    private BaseObject baseObject;

    @Test
    public void testObjectIsCreated() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("BaseObject is null!", baseObject);
    }


    @Test
    public void testStringIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("String is not being filled!", baseObject.getText());
    }


    @Test
    public void testIntegerIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("Integer is not being filled!", baseObject.getIntegerNumber());
    }


    @Test
    public void testPrimitiveIntegerIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("primitive Integer (int) is not being filled!", baseObject.getPrimitiveIntNumber());
    }


    @Test
    public void testFloatIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("Float is not being filled!", baseObject.getFloatNumber());
    }


    @Test
    public void testPrimitiveFloatIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("primitive Float (float) is not being filled!", baseObject.getPrimitiveFloatNumber());
    }


    @Test
    public void testLongIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("Long is not being filled!", baseObject.getLongNumber());
    }


    @Test
    public void testPrimitiveLongIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("primitive Long (long) is not being filled!", baseObject.getPrimitiveLongNumber());
    }


    @Test
    public void testDoubleIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("Double is not being filled!", baseObject.getDoubleNumber());
    }


    @Test
    public void testPrimitiveDoubleIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("primitive Double (double) is not being filled!", baseObject.getPrimitiveDouble());
    }


    @Test
    public void testBooleanIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("Boolean is not being filled!", baseObject.getBooleanObject());
    }


    @Test
    public void testPrimitiveBooleanIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("primitive Boolean (boolean) is not being filled!", baseObject.getPrimitiveBoolean());
    }


    @Test
    public void testByteIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("Byte is not being filled!", baseObject.getByteObject());
    }


    @Test
    public void testPrimitiveByteIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);

        assertNotNull("primitive Byte (byte) is not being filled!", baseObject.getPrimitiveByte());
    }


    @Test
    public void testBigIntegerIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("BigInteger is not being filled!", baseObject.getBigIntegerNumber());
    }


    @Test
    public void testBigDecimalIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("BigDecimal is not being filled!", baseObject.getBigDecimalNumber());
    }


    @Test
    public void testDateIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("Date is not being filled!", baseObject.getDate());
    }


    @Test
    public void testObjectShortIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("ObjectShort is not being filled!", baseObject.getObjectShort());
    }


    @Test
    public void testPrimitiveShortIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("PrimitiveShort is not being filled!", baseObject.getPrimitiveShort());
    }


    @Test
    public void testCharacterIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("Character is not being filled!", baseObject.getCharacter());
    }


    @Test
    public void testPrimitiveCharacterIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertNotNull("char is not being filled!", baseObject.getPrimitiveCharacter());
    }


    @Test
    public void testAddedCreatorIsUsed() throws FillingException {

        StringCreator stringCreator = mock(StringCreator.class);
        when(stringCreator.create()).thenReturn("test");

        beanfiller.addCreator(String.class, stringCreator);

        beanfiller.fillBean(BaseObject.class);

        // assert that our mock was called
        verify(stringCreator).create();
    }


    @Test
    public void testAddedSpecificCreatorIsUsed() throws FillingException {

        StringCreator stringCreator = mock(StringCreator.class);
        when(stringCreator.create()).thenReturn("test");

        beanfiller.addCreatorForClassAndAttribute(BaseObject.class, "text", stringCreator);

        beanfiller.fillBean(BaseObject.class);

        // assert that our mock was called
        verify(stringCreator).create();
    }


    @Test(expected = WrongCreatorException.class)
    public void testWrongCreatorExceptionIsThrownIfNonSimpleCreatorIsUsed() throws FillingException {

        EnumCreator enumCreator = new SimpleEnumCreator();

        beanfiller.addCreatorForClassAndAttribute(BaseObject.class, "text", enumCreator);

        beanfiller.fillBean(BaseObject.class);
    }


    @Test
    public void testSetterObjectDiffersFromFieldObject() throws FillingException {

        BaseObject object = beanfiller.fillBean(BaseObject.class);
        assertNotEquals(0, object.getDateFromTimestamp());
    }
}
