
package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Test;

import org.synyx.beanfiller.creator.Creator;
import org.synyx.beanfiller.creator.SimpleCreator;

import java.util.HashMap;
import java.util.Map;


/**
 * Tests of the different setters and Constructors.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class SettersAndConstructorsTest {

    @Test
    public void testDefaultCreatorMapIsUsed() {

        BeanFiller beanfiller = new BeanFiller();
        Assert.assertNotNull("The CreatorMap shouldn't be null if the default Creator map is used!",
            beanfiller.getCreatorMap());
        Assert.assertEquals("The CreatorMap shouldn't be empty if the default Creator map is used!", false,
            beanfiller.getCreatorMap().isEmpty());
    }


    @Test
    public void testSetCreatorMapWithConstructor() {

        BeanFiller beanfiller = new BeanFiller(new HashMap<String, Creator>());
        Assert.assertNotNull("The CreatorMap shouldn't be null", beanfiller.getCreatorMap());
        Assert.assertEquals("The CreatorMap should be empty as we set it above!", true,
            beanfiller.getCreatorMap().isEmpty());
    }


    /**
     * Test the adding of Creators to the map.
     */
    @Test
    public void testAddCreator() {

        BeanFiller beanfiller = new BeanFiller();

        SimpleCreator Creator = new org.synyx.beanfiller.creator.StringCreator();
        beanfiller.addCreator(this.getClass(), Creator);

        Map<String, Creator> CreatorMap = beanfiller.getCreatorMap();

        Assert.assertEquals("Creator wasn't added under the expected key!", Creator,
            CreatorMap.get(this.getClass().getName()));
    }


    /**
     * Test the adding of a Creator for an Array class to the map.
     */
    @Test
    public void testAddArrayCreator() {

        BeanFiller beanfiller = new BeanFiller();

        SimpleCreator Creator = new org.synyx.beanfiller.creator.StringCreator();
        beanfiller.addCreator(String[].class, Creator);

        Map<String, Creator> CreatorMap = beanfiller.getCreatorMap();

        Assert.assertEquals("Creator wasn't added under the expected key!", Creator,
            CreatorMap.get("[Ljava.lang.String;"));
    }


    /**
     * Test the adding of Creators to the map is not done without a class given.
     */
    @Test
    public void testAddCreatorWithoutClass() {

        // we need an empty Creator map for this test
        BeanFiller beanfiller = new BeanFiller(new HashMap<String, Creator>());

        SimpleCreator Creator = new org.synyx.beanfiller.creator.StringCreator();
        beanfiller.addCreator(null, Creator);

        Map<String, Creator> CreatorMap = beanfiller.getCreatorMap();

        Assert.assertEquals("Creator map should be empty!", 0, CreatorMap.size());
    }


    /**
     * Test the adding of Creators to the map is not done without a Creator given.
     */
    @Test
    public void testAddCreatorWithoutCreator() {

        // we need an empty Creator map for this test
        BeanFiller beanfiller = new BeanFiller(new HashMap<String, Creator>());

        beanfiller.addCreator(this.getClass(), null);

        Map<String, Creator> CreatorMap = beanfiller.getCreatorMap();

        Assert.assertEquals("Creator map should be empty!", 0, CreatorMap.size());
    }


    /**
     * Test the adding of Creators to the specific Creators map.
     */
    @Test
    public void testAddSpecificCreator() {

        BeanFiller beanfiller = new BeanFiller();

        SimpleCreator Creator = new org.synyx.beanfiller.creator.StringCreator();
        beanfiller.addCreatorForClassAndAttribute(this.getClass(), "test", Creator);

        Map<String, Creator> CreatorMap = beanfiller.getClassAndAttributeSpecificCreatorMap();

        Assert.assertEquals("Creator wasn't added under the expected key!", Creator,
            CreatorMap.get(this.getClass().getName() + ".test"));
    }


    /**
     * Test the adding of Creators to the specific Creators map is not done without a class given.
     */
    @Test
    public void testAddSpecificCreatorWithoutClass() {

        // we need an empty Creator map for this test
        BeanFiller beanfiller = new BeanFiller(new HashMap<String, Creator>());

        SimpleCreator Creator = new org.synyx.beanfiller.creator.StringCreator();
        beanfiller.addCreatorForClassAndAttribute(null, "test", Creator);

        Map<String, Creator> CreatorMap = beanfiller.getClassAndAttributeSpecificCreatorMap();

        Assert.assertEquals("Creator map should be empty!", 0, CreatorMap.size());
    }


    /**
     * Test the adding of Creators to the specific Creators map is not done without an attributeName given.
     */
    @Test
    public void testAddSpecificCreatorWithoutAttributeName() {

        // we need an empty Creator map for this test
        BeanFiller beanfiller = new BeanFiller(new HashMap<String, Creator>());

        SimpleCreator Creator = new org.synyx.beanfiller.creator.StringCreator();
        beanfiller.addCreatorForClassAndAttribute(this.getClass(), null, Creator);

        Map<String, Creator> CreatorMap = beanfiller.getClassAndAttributeSpecificCreatorMap();

        Assert.assertEquals("Creator map should be empty!", 0, CreatorMap.size());
    }


    /**
     * Test the adding of Creators to the specific Creators map is not done without a Creator given.
     */
    @Test
    public void testAddSpecificCreatorWithoutCreator() {

        // we need an empty Creator map for this test
        BeanFiller beanfiller = new BeanFiller(new HashMap<String, Creator>());

        beanfiller.addCreatorForClassAndAttribute(this.getClass(), "test", null);

        Map<String, Creator> CreatorMap = beanfiller.getClassAndAttributeSpecificCreatorMap();

        Assert.assertEquals("Creator map should be empty!", 0, CreatorMap.size());
    }
}
