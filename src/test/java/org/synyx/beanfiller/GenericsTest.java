package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mockito;

import org.synyx.beanfiller.creator.ListCreator;
import org.synyx.beanfiller.creator.SimpleCreator;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.exceptions.WrongCreatorException;
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

        genericsObject = beanfiller.fillBean(GenericsObject.class);
        Assert.assertNotNull("GenericsObject is null!", genericsObject);
    }


    @Test
    public void testListIsFilled() throws FillingException {

        genericsObject = beanfiller.fillBean(GenericsObject.class);
        Assert.assertNotNull("StringList is null!", genericsObject.getStringList());
        Assert.assertFalse("StringList is empty!", genericsObject.getStringList().isEmpty());
    }


    @Test
    public void testMapIsFilled() throws FillingException {

        genericsObject = beanfiller.fillBean(GenericsObject.class);
        Assert.assertNotNull("StringMap is null!", genericsObject.getStringMap());
        Assert.assertFalse("StringMap is empty!", genericsObject.getStringMap().isEmpty());
    }


    @Test
    public void testListMapIsFilled() throws FillingException {

        genericsObject = beanfiller.fillBean(GenericsObject.class);
        Assert.assertNotNull("StringListMap is null!", genericsObject.getStringListMap());
        Assert.assertFalse("StringListMap is empty!", genericsObject.getStringListMap().isEmpty());
    }


    @Test
    public void testAddedGenericsCreatorIsUsed() throws FillingException {

        ListCreator listCreator = mock(ListCreator.class);

        when(listCreator.createCollection(Mockito.anyList())).thenReturn(new ArrayList<String>());

        beanfiller.addCreator(List.class, listCreator);

        beanfiller.fillBean(SimpleGenericsObject.class);

        // assert that our mock was called
        verify(listCreator).createCollection(Mockito.anyList());
    }


    @Test
    public void testAddedSpecificGenericsCreatorIsUsed() throws FillingException {

        ListCreator listCreator = mock(ListCreator.class);
        when(listCreator.createCollection(Mockito.anyList())).thenReturn(new ArrayList<String>());
        beanfiller.addCreatorForClassAndAttribute(SimpleGenericsObject.class, "stringList", listCreator);

        beanfiller.fillBean(SimpleGenericsObject.class);

        // assert that our mock was called
        verify(listCreator).createCollection(Mockito.anyList());
    }


    @Test(expected = WrongCreatorException.class)
    public void testWrongCreatorExceptionIsThrownIfNonGenericsCreatorIsUsed() throws FillingException {

        SimpleCreator stringCreator = new org.synyx.beanfiller.creator.StringCreator();

        beanfiller.addCreatorForClassAndAttribute(GenericsObject.class, "stringList", stringCreator);

        beanfiller.fillBean(GenericsObject.class);
    }
}
