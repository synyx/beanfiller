
package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Test;

import org.synyx.beanfiller.builder.Builder;

import java.util.HashMap;
import java.util.Map;


/**
 * Tests of the different setters and Constructors.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class SettersAndConstructorsTest {

    @Test
    public void testDefaultBuilderMapIsUsed() {

        BeanFiller beanfiller = new BeanFiller();
        Assert.assertNotNull("The BuilderMap shouldn't be null if the default builder map is used!",
            beanfiller.getBuilderMap());
        Assert.assertEquals("The BuilderMap shouldn't be empty if the default builder map is used!", false,
            beanfiller.getBuilderMap().isEmpty());
    }


    @Test
    public void testSetBuilderMapWithConstructor() {

        BeanFiller beanfiller = new BeanFiller(new HashMap<String, Builder>());
        Assert.assertNotNull("The BuilderMap shouldn't be null", beanfiller.getBuilderMap());
        Assert.assertEquals("The BuilderMap should be empty as we set it above!", true,
            beanfiller.getBuilderMap().isEmpty());
    }


    /**
     * Test the adding of Builders to the map.
     */
    @Test
    public void testAddBuilder() {

        BeanFiller beanfiller = new BeanFiller();

        Builder builder = new org.synyx.beanfiller.builder.StringBuilder();
        beanfiller.addBuilder(this.getClass(), builder);

        Map<String, Builder> builderMap = beanfiller.getBuilderMap();

        Assert.assertEquals("Builder wasn't added under the expected key!", builder,
            builderMap.get(this.getClass().getName()));
    }


    /**
     * Test the adding of a Builder for an Array class to the map.
     */
    @Test
    public void testAddArrayBuilder() {

        BeanFiller beanfiller = new BeanFiller();

        Builder builder = new org.synyx.beanfiller.builder.StringBuilder();
        beanfiller.addBuilder(String[].class, builder);

        Map<String, Builder> builderMap = beanfiller.getBuilderMap();

        Assert.assertEquals("Builder wasn't added under the expected key!", builder,
            builderMap.get("[Ljava.lang.String;"));
    }


    /**
     * Test the adding of Builders to the map is not done without a class given.
     */
    @Test
    public void testAddBuilderWithoutClass() {

        // we need an empty builder map for this test
        BeanFiller beanfiller = new BeanFiller(new HashMap<String, Builder>());

        Builder builder = new org.synyx.beanfiller.builder.StringBuilder();
        beanfiller.addBuilder(null, builder);

        Map<String, Builder> builderMap = beanfiller.getBuilderMap();

        Assert.assertEquals("Builder map should be empty!", 0, builderMap.size());
    }


    /**
     * Test the adding of Builders to the map is not done without a builder given.
     */
    @Test
    public void testAddBuilderWithoutBuilder() {

        // we need an empty builder map for this test
        BeanFiller beanfiller = new BeanFiller(new HashMap<String, Builder>());

        beanfiller.addBuilder(this.getClass(), null);

        Map<String, Builder> builderMap = beanfiller.getBuilderMap();

        Assert.assertEquals("Builder map should be empty!", 0, builderMap.size());
    }


    /**
     * Test the adding of Builders to the specific builders map.
     */
    @Test
    public void testAddSpecificBuilder() {

        BeanFiller beanfiller = new BeanFiller();

        Builder builder = new org.synyx.beanfiller.builder.StringBuilder();
        beanfiller.addBuilderForClassAndAttribute(this.getClass(), "test", builder);

        Map<String, Builder> builderMap = beanfiller.getClassAndAttributeSpecificBuilderMap();

        Assert.assertEquals("Builder wasn't added under the expected key!", builder,
            builderMap.get(this.getClass().getName() + ".test"));
    }


    /**
     * Test the adding of Builders to the specific builders map is not done without a class given.
     */
    @Test
    public void testAddSpecificBuilderWithoutClass() {

        // we need an empty builder map for this test
        BeanFiller beanfiller = new BeanFiller(new HashMap<String, Builder>());

        Builder builder = new org.synyx.beanfiller.builder.StringBuilder();
        beanfiller.addBuilderForClassAndAttribute(null, "test", builder);

        Map<String, Builder> builderMap = beanfiller.getClassAndAttributeSpecificBuilderMap();

        Assert.assertEquals("Builder map should be empty!", 0, builderMap.size());
    }


    /**
     * Test the adding of Builders to the specific builders map is not done without an attributeName given.
     */
    @Test
    public void testAddSpecificBuilderWithoutAttributeName() {

        // we need an empty builder map for this test
        BeanFiller beanfiller = new BeanFiller(new HashMap<String, Builder>());

        Builder builder = new org.synyx.beanfiller.builder.StringBuilder();
        beanfiller.addBuilderForClassAndAttribute(this.getClass(), null, builder);

        Map<String, Builder> builderMap = beanfiller.getClassAndAttributeSpecificBuilderMap();

        Assert.assertEquals("Builder map should be empty!", 0, builderMap.size());
    }


    /**
     * Test the adding of Builders to the specific builders map is not done without a builder given.
     */
    @Test
    public void testAddSpecificBuilderWithoutBuilder() {

        // we need an empty builder map for this test
        BeanFiller beanfiller = new BeanFiller(new HashMap<String, Builder>());

        beanfiller.addBuilderForClassAndAttribute(this.getClass(), "test", null);

        Map<String, Builder> builderMap = beanfiller.getClassAndAttributeSpecificBuilderMap();

        Assert.assertEquals("Builder map should be empty!", 0, builderMap.size());
    }
}
