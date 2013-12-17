
package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Test;

import org.synyx.beanfiller.creator.EnumCreator;
import org.synyx.beanfiller.creator.SimpleEnumCreator;
import org.synyx.beanfiller.creator.StringCreator;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.exceptions.WrongCreatorException;
import org.synyx.beanfiller.testobjects.BaseObject;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Tests for the basic types.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class BasicTest {

    private BeanFiller beanfiller = new BeanFiller();
    private BaseObject baseObject;

    @Test
    public void testObjectIsCreated() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        Assert.assertNotNull("BaseObject is null!", baseObject);
    }


    @Test
    public void testStringIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        ;
        Assert.assertNotNull("String is not beeing filled!", baseObject.getText());
    }


    @Test
    public void testIntegerIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        ;
        Assert.assertNotNull("Integer is not beeing filled!", baseObject.getIntegerNumber());
    }


    @Test
    public void testPrimitiveIntegerIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        ;
        Assert.assertNotNull("primitive Integer (int) is not beeing filled!", baseObject.getPrimitiveIntNumber());
    }


    @Test
    public void testFloatIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        ;
        Assert.assertNotNull("Float is not beeing filled!", baseObject.getFloatNumber());
    }


    @Test
    public void testPrimitiveFloatIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        ;
        Assert.assertNotNull("primitive Float (float) is not beeing filled!", baseObject.getPrimitiveFloatNumber());
    }


    @Test
    public void testLongIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        ;
        Assert.assertNotNull("Long is not beeing filled!", baseObject.getLongNumber());
    }


    @Test
    public void testPrimitiveLongIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        ;
        Assert.assertNotNull("primitive Long (long) is not beeing filled!", baseObject.getPrimitiveLongNumber());
    }


    @Test
    public void testDoubleIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        ;
        Assert.assertNotNull("Double is not beeing filled!", baseObject.getDoubleNumber());
    }


    @Test
    public void testPrimitiveDoubleIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        ;
        Assert.assertNotNull("primitive Double (double) is not beeing filled!", baseObject.getPrimitiveDouble());
    }


    @Test
    public void testBooleanIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        ;
        Assert.assertNotNull("Boolean is not beeing filled!", baseObject.getBooleanObject());
    }


    @Test
    public void testPrimitiveBooleanIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        ;
        Assert.assertNotNull("primitive Boolean (boolean) is not beeing filled!", baseObject.getPrimitiveBoolean());
    }


    @Test
    public void testByteIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        Assert.assertNotNull("Byte is not beeing filled!", baseObject.getByteObject());
    }


    @Test
    public void testPrimitiveByteIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);

        Assert.assertNotNull("primitive Byte (byte) is not beeing filled!", baseObject.getPrimitiveByte());
    }


    @Test
    public void testBigIntegerIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        Assert.assertNotNull("BigInteger is not beeing filled!", baseObject.getBigIntegerNumber());
    }


    @Test
    public void testBigDecimalIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        Assert.assertNotNull("BigDecimal is not beeing filled!", baseObject.getBigDecimalNumber());
    }


    @Test
    public void testDateIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        Assert.assertNotNull("Date is not beeing filled!", baseObject.getDate());
    }


    @Test
    public void testObjectShortIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        Assert.assertNotNull("ObjectShort is not beeing filled!", baseObject.getObjectShort());
    }


    @Test
    public void testPrimitiveShortIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        Assert.assertNotNull("PrimitiveShort is not beeing filled!", baseObject.getPrimitiveShort());
    }


    @Test
    public void testCharacterIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        Assert.assertNotNull("Character is not beeing filled!", baseObject.getCharacter());
    }


    @Test
    public void testPrimitiveCharacterIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        Assert.assertNotNull("char is not beeing filled!", baseObject.getPrimitiveCharacter());
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
        Assert.assertNotNull(object.getDateTime());
    }
}
