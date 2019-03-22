
package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Test;

import org.synyx.beanfiller.creator.Creator;
import org.synyx.beanfiller.creator.SimpleCreator;
import org.synyx.beanfiller.util.RandomGenerator;

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
        Assert.assertFalse("The CreatorMap shouldn't be empty if the default Creator map is used!",
            beanfiller.getCreatorMap().isEmpty());
    }


    @Test
    public void testSetCreatorMapWithConstructor() {

        BeanFiller beanfiller = new BeanFiller(new HashMap<>());
        Assert.assertNotNull("The CreatorMap shouldn't be null", beanfiller.getCreatorMap());
        Assert.assertTrue("The CreatorMap should be empty as we set it above!", beanfiller.getCreatorMap().isEmpty());
    }


    /**
     * Test the adding of Creators to the map.
     */
    @Test
    public void testAddCreator() {

        BeanFiller beanfiller = new BeanFiller();

        SimpleCreator creator = new org.synyx.beanfiller.creator.StringCreator(new RandomGenerator());
        beanfiller.addCreator(this.getClass(), creator);

        Map<String, Creator> creatorMap = beanfiller.getCreatorMap();

        Assert.assertEquals("Creator wasn't added under the expected key!", creator,
            creatorMap.get(this.getClass().getName()));
    }


    /**
     * Test the adding of a Creator for an Array class to the map.
     */
    @Test
    public void testAddArrayCreator() {

        BeanFiller beanfiller = new BeanFiller();

        SimpleCreator creator = new org.synyx.beanfiller.creator.StringCreator(new RandomGenerator());
        beanfiller.addCreator(String[].class, creator);

        Map<String, Creator> creatorMap = beanfiller.getCreatorMap();

        Assert.assertEquals("Creator wasn't added under the expected key!", creator,
            creatorMap.get("[Ljava.lang.String;"));
    }


    /**
     * Test the adding of Creators to the map is not done without a class given.
     */
    @Test
    public void testAddCreatorWithoutClass() {

        // we need an empty Creator map for this test
        BeanFiller beanfiller = new BeanFiller(new HashMap<>());

        SimpleCreator creator = new org.synyx.beanfiller.creator.StringCreator(new RandomGenerator());
        beanfiller.addCreator(null, creator);

        Map<String, Creator> creatorMap = beanfiller.getCreatorMap();

        Assert.assertEquals("Creator map should be empty!", 0, creatorMap.size());
    }


    /**
     * Test the adding of Creators to the map is not done without a Creator given.
     */
    @Test
    public void testAddCreatorWithoutCreator() {

        // we need an empty Creator map for this test
        BeanFiller beanfiller = new BeanFiller(new HashMap<>());

        beanfiller.addCreator(this.getClass(), null);

        Map<String, Creator> creatorMap = beanfiller.getCreatorMap();

        Assert.assertEquals("Creator map should be empty!", 0, creatorMap.size());
    }


    /**
     * Test the adding of Creators to the specific Creators map.
     */
    @Test
    public void testAddSpecificCreator() {

        BeanFiller beanfiller = new BeanFiller();

        SimpleCreator creator = new org.synyx.beanfiller.creator.StringCreator(new RandomGenerator());
        beanfiller.addCreatorForClassAndAttribute(this.getClass(), "test", creator);

        Map<String, Creator> creatorMap = beanfiller.getClassAndAttributeSpecificCreatorMap();

        Assert.assertEquals("Creator wasn't added under the expected key!", creator,
            creatorMap.get(this.getClass().getName() + ".test"));
    }


    /**
     * Test the adding of Creators to the specific Creators map is not done without a class given.
     */
    @Test
    public void testAddSpecificCreatorWithoutClass() {

        // we need an empty Creator map for this test
        BeanFiller beanfiller = new BeanFiller(new HashMap<>());

        SimpleCreator creator = new org.synyx.beanfiller.creator.StringCreator(new RandomGenerator());
        beanfiller.addCreatorForClassAndAttribute(null, "test", creator);

        Map<String, Creator> creatorMap = beanfiller.getClassAndAttributeSpecificCreatorMap();

        Assert.assertEquals("Creator map should be empty!", 0, creatorMap.size());
    }


    /**
     * Test the adding of Creators to the specific Creators map is not done without an attributeName given.
     */
    @Test
    public void testAddSpecificCreatorWithoutAttributeName() {

        // we need an empty Creator map for this test
        BeanFiller beanfiller = new BeanFiller(new HashMap<>());

        SimpleCreator creator = new org.synyx.beanfiller.creator.StringCreator(new RandomGenerator());
        beanfiller.addCreatorForClassAndAttribute(this.getClass(), null, creator);

        Map<String, Creator> creatorMap = beanfiller.getClassAndAttributeSpecificCreatorMap();

        Assert.assertEquals("Creator map should be empty!", 0, creatorMap.size());
    }


    /**
     * Test the adding of Creators to the specific Creators map is not done without a Creator given.
     */
    @Test
    public void testAddSpecificCreatorWithoutCreator() {

        // we need an empty Creator map for this test
        BeanFiller beanfiller = new BeanFiller(new HashMap<>());

        beanfiller.addCreatorForClassAndAttribute(this.getClass(), "test", null);

        Map<String, Creator> creatorMap = beanfiller.getClassAndAttributeSpecificCreatorMap();

        Assert.assertEquals("Creator map should be empty!", 0, creatorMap.size());
    }
}
