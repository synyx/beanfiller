package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mockito;

import org.synyx.beanfiller.creator.GenericsCreator;
import org.synyx.beanfiller.creator.ListCreator;
import org.synyx.beanfiller.creator.SimpleCreator;
import org.synyx.beanfiller.testobjects.GenericsObject;
import org.synyx.beanfiller.testobjects.SimpleGenericsObject;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Tests for Objects with generic Types.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class GenericsTest {

    private BeanFiller beanfiller = new BeanFiller();
    private GenericsObject genericsObject;

    @Test
    public void testGenericsObjectIsCreated() throws FillingException {

        genericsObject = beanfiller.fillBean(new GenericsObject());
        Assert.assertNotNull("GenericsObject is null!", genericsObject);
    }


    @Test
    public void testListIsFilled() throws FillingException {

        genericsObject = beanfiller.fillBean(new GenericsObject());
        Assert.assertNotNull("StringList is null!", genericsObject.getStringList());
        Assert.assertFalse("StringList is empty!", genericsObject.getStringList().isEmpty());
    }


    @Test
    public void testMapIsFilled() throws FillingException {

        genericsObject = beanfiller.fillBean(new GenericsObject());
        Assert.assertNotNull("StringMap is null!", genericsObject.getStringMap());
        Assert.assertFalse("StringMap is empty!", genericsObject.getStringMap().isEmpty());
    }


    @Test
    public void testListMapIsFilled() throws FillingException {

        genericsObject = beanfiller.fillBean(new GenericsObject());
        Assert.assertNotNull("StringListMap is null!", genericsObject.getStringListMap());
        Assert.assertFalse("StringListMap is empty!", genericsObject.getStringListMap().isEmpty());
    }


    @Test
    public void testAddedGenericsCreatorIsUsed() throws FillingException {

        GenericsCreator genericsCreator = mock(ListCreator.class);
        when(genericsCreator.createWithGenerics(Mockito.anyList())).thenReturn(new ArrayList<String>());
        beanfiller.addCreator(List.class, genericsCreator);

        beanfiller.fillBean(new SimpleGenericsObject());

        // assert that our mock was called
        verify(genericsCreator).createWithGenerics(Mockito.anyList());
    }


    @Test
    public void testAddedSpecificGenericsCreatorIsUsed() throws FillingException {

        GenericsCreator genericsCreator = mock(ListCreator.class);
        when(genericsCreator.createWithGenerics(Mockito.anyList())).thenReturn(new ArrayList<String>());
        beanfiller.addCreatorForClassAndAttribute(SimpleGenericsObject.class, "stringList", genericsCreator);

        beanfiller.fillBean(new SimpleGenericsObject());

        // assert that our mock was called
        verify(genericsCreator).createWithGenerics(Mockito.anyList());
    }


    @Test(expected = WrongCreatorException.class)
    public void testWrongCreatorExceptionIsThrownIfNonGenericsCreatorIsUsed() throws FillingException {

        SimpleCreator stringCreator = new org.synyx.beanfiller.creator.StringCreator();

        beanfiller.addCreatorForClassAndAttribute(GenericsObject.class, "stringList", stringCreator);

        beanfiller.fillBean(new GenericsObject());
    }
}
