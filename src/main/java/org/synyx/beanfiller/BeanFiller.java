package org.synyx.beanfiller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.synyx.beanfiller.creator.ArrayCreator;
import org.synyx.beanfiller.creator.BigDecimalCreator;
import org.synyx.beanfiller.creator.BigIntegerCreator;
import org.synyx.beanfiller.creator.BooleanCreator;
import org.synyx.beanfiller.creator.ByteCreator;
import org.synyx.beanfiller.creator.CharCreator;
import org.synyx.beanfiller.creator.Creator;
import org.synyx.beanfiller.creator.DateCreator;
import org.synyx.beanfiller.creator.DoubleCreator;
import org.synyx.beanfiller.creator.EnumCreator;
import org.synyx.beanfiller.creator.FloatCreator;
import org.synyx.beanfiller.creator.IntegerCreator;
import org.synyx.beanfiller.creator.ListCreator;
import org.synyx.beanfiller.creator.LongCreator;
import org.synyx.beanfiller.creator.MapCreator;
import org.synyx.beanfiller.creator.ShortCreator;
import org.synyx.beanfiller.creator.SimpleArrayCreator;
import org.synyx.beanfiller.creator.SimpleEnumCreator;
import org.synyx.beanfiller.creator.StringCreator;
import org.synyx.beanfiller.domain.ObjectInformation;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.services.CreatorRegistry;
import org.synyx.beanfiller.strategies.AbstractCreatorStrategy;
import org.synyx.beanfiller.strategies.StrategyManager;
import org.synyx.beanfiller.util.RandomGenerator;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class for recursively filling Beans with random data. As of now, this class only uses public setters that have the
 * same name as the member they set for filling the beans, so ensure that your beans you want to fill provide a setter
 * for every variable that should be filled and that it meets the naming convention for setters ('setVariableName').
 * Also note that only the first parameter of the setters is used. With these conventions the BeanFiller does not
 * invoke literally everything that is named 'set*' and so is less error prone.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class BeanFiller {

    private static final Logger LOG = LoggerFactory.getLogger(BeanFiller.class);
    private final CreatorRegistry creatorRegistry;
    private final StrategyManager strategyManager;
    private final RandomGenerator randomGenerator;

    /**
     * Create a new instance of the BeanFiller and use the default set of Creators that come with it (Creators for
     * specific classes can be added with addOrReplaceCreator, Creators for a specific field of a specific class can be
     * added with addCreatorForClassAndAttribute).
     */
    public BeanFiller() {

        this(null);
    }


    /**
     * Instantiates the BeanFiller with the given creatorMap instead of the default one. The creator Map consists of a
     * mapping of the full qualified class names of classes to the Creator to use for them.
     *
     * @param  creatorMap  specific creatorMap .
     */
    public BeanFiller(Map<String, Creator> creatorMap) {

        randomGenerator = new RandomGenerator();

        if (creatorMap == null) {
            creatorRegistry = new CreatorRegistry(getDefaultCreatorMap());
        } else {
            creatorRegistry = new CreatorRegistry(creatorMap);
        }

        strategyManager = new StrategyManager(creatorRegistry);
    }

    /**
     * Creates an instance of the given class and fills in recursively.
     *
     * @param  <T>  Type of the class to fill.
     * @param  clazz  class to fill
     *
     * @return  Instance of the filled class
     *
     * @throws  FillingException
     */
    public <T> T fillBean(Class<T> clazz) throws FillingException {

        ObjectInformation information = new ObjectInformation(clazz, null, null, null, null, null);
        AbstractCreatorStrategy strategy = strategyManager.getStrategyFor(information);

        return (T) strategy.createObject(information);
    }


    /**
     * Adds or replaces the Creator for the given class. This Creators have a lower priority than the ones specified
     * for the attributes of classes.<br/>
     * NOTES:<br/>
     * For replacing the default EnumCreator, call with Enum.class<br/>
     * For replacing the default ArrayCreator, call with org.synyx.beanfiller.creator.ArrayCreator.class<br/>
     * For arrays, use the array classes - e.g. String[].class
     *
     * @param  clazz  class for which the creator should be used
     * @param  creator  creator that should be used for the given class
     */
    public void addCreator(Class clazz, Creator creator) {

        if (clazz == null || creator == null) {
            LOG.warn("Class or Creator is null, abort adding the Creator!");

            return;
        }

        LOG.debug("Adding  Creator for class : " + clazz.getName()
            + ". Added Creator: " + creator.getClass().getName());
        creatorRegistry.addCreator(clazz, creator);
    }


    /**
     * Add a creator that is only used for the given attribute of the given class. The creators specified here have
     * higher priority than the class specific ones.
     *
     * @param  clazz  class for which the creator should be used (for arrays, use the array classes - e.g.
     *                String[].class !)
     * @param  attributeName  attribute for which the creator should be used
     * @param  creator  creator that should be used for the given attribute of the given class
     */
    public void addCreatorForClassAndAttribute(Class clazz, String attributeName, Creator creator) {

        if (clazz == null || attributeName == null || creator == null) {
            LOG.warn("Class, attributeName, or Creator is null, abort adding the creator!");

            return;
        }

        LOG.debug("adding attribute specific creator for class and attribute: " + clazz.getName() + " - "
            + attributeName
            + ". Added creator: " + creator.getClass().getName());
        creatorRegistry.addCreatorForClassAndAttribute(clazz, attributeName, creator);
    }


    /**
     * Get the currently used creator map.
     *
     * @return  the Map with the currently registered Creators.
     */
    public Map<String, Creator> getCreatorMap() {

        return creatorRegistry.getCreatorMap();
    }


    /**
     * Get the currently used class and attribute specific creator map.
     *
     * @return  the Map with the currently registered class and attribute specific Creators.
     */
    public Map<String, Creator> getClassAndAttributeSpecificCreatorMap() {

        return creatorRegistry.getClassAndAttributeSpecificCreatorMap();
    }


    /**
     * Gets the default creator Map that contains the basic set of creators.
     *
     * @return  Map of Creators
     */
    private Map<String, Creator> getDefaultCreatorMap() {

        Map<String, Creator> map = new HashMap<>();

        map.put(String.class.getName(), new StringCreator(randomGenerator));

        IntegerCreator integerCreator = new IntegerCreator(randomGenerator);
        map.put("int", integerCreator);
        map.put(Integer.class.getName(), integerCreator);

        FloatCreator floatCreator = new FloatCreator(randomGenerator);
        map.put("float", floatCreator);
        map.put(Float.class.getName(), floatCreator);

        LongCreator longCreator = new LongCreator(randomGenerator);
        map.put("long", longCreator);
        map.put(Long.class.getName(), longCreator);

        DoubleCreator doubleCreator = new DoubleCreator(randomGenerator);
        map.put("double", doubleCreator);
        map.put(Double.class.getName(), doubleCreator);

        BooleanCreator booleanCreator = new BooleanCreator(randomGenerator);
        map.put("boolean", booleanCreator);
        map.put(Boolean.class.getName(), booleanCreator);

        ByteCreator byteCreator = new ByteCreator(randomGenerator);
        map.put("byte", byteCreator);
        map.put(Byte.class.getName(), byteCreator);

        BigIntegerCreator bigIntegerCreator = new BigIntegerCreator(randomGenerator);
        map.put(BigInteger.class.getName(), bigIntegerCreator);

        BigDecimalCreator bigDecimalCreator = new BigDecimalCreator(randomGenerator);
        map.put(BigDecimal.class.getName(), bigDecimalCreator);

        MapCreator mapCreator = new MapCreator();
        map.put(Map.class.getName(), mapCreator);

        ListCreator listCreator = new ListCreator();
        map.put(List.class.getName(), listCreator);

        EnumCreator enumCreator = new SimpleEnumCreator();
        map.put(Enum.class.getName(), enumCreator);

        ArrayCreator arrayCreator = new SimpleArrayCreator();
        map.put("org.synyx.beanfiller.creator.ArrayCreator", arrayCreator);

        DateCreator dateCreator = new DateCreator(randomGenerator);
        map.put(Date.class.getName(), dateCreator);

        CharCreator charCreator = new CharCreator(randomGenerator);
        map.put(Character.class.getName(), charCreator);
        map.put("char", charCreator);

        ShortCreator shortCreator = new ShortCreator(randomGenerator);
        map.put(Short.class.getName(), shortCreator);
        map.put("short", shortCreator);

        return map;
    }
}
