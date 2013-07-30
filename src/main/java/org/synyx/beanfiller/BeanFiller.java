package org.synyx.beanfiller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.synyx.beanfiller.creator.Creator;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.services.CreatorRegistry;
import org.synyx.beanfiller.strategies.AbstractCreatorStrategy;
import org.synyx.beanfiller.strategies.StrategyManager;

import java.util.Map;


/**
 * Class for recursively filling Beans with random data. As of now, this class only uses public setters for filling the
 * beans, so ensure that your beans you want to fill provide a setter for every variable that should be filled and that
 * it meets the naming convention for setters ('setVariableName').
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class BeanFiller {

    private static final Logger LOG = LoggerFactory.getLogger(BeanFiller.class);
    private CreatorRegistry creatorRegistry;
    private StrategyManager strategyManager;

    /**
     * Create a new instance of the BeanFiller and use the default set of Creators that come with it (Creators for
     * specific classes can be added with addOrReplaceCreator, Creators for a specific field of a specific class can be
     * added with addCreatorForClassAndAttribute).
     */
    public BeanFiller() {

        this(null);
    }


    /**
     * Instanciates the BeanFiller with the given creatorMap instead of the default one. The creator Map consists of a
     * mapping of the full qualified class names of classes to the Creator to use for them.
     *
     * @param  creatorMap  specific creatorMap .
     */
    public BeanFiller(Map<String, Creator> creatorMap) {

        creatorRegistry = new CreatorRegistry(creatorMap);
        strategyManager = new StrategyManager(creatorRegistry);
    }

    public <T> T fillBean(Class<T> clazz) throws FillingException {

        ObjectInformation information = new ObjectInformation(clazz, null, null, null, null);
        AbstractCreatorStrategy strategy = strategyManager.getStrategyFor(information);

        return (T) strategy.createObject(information);
    }


    /**
     * Adds or replaces the Creator for the given class. This Creators have a lower priority than the ones specified for
     * the attributes of classes.<br/>
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
     * @return
     */
    public Map<String, Creator> getCreatorMap() {

        return creatorRegistry.getCreatorMap();
    }


    /**
     * Get the currently used class and attribute specific creator map.
     *
     * @return
     */
    public Map<String, Creator> getClassAndAttributeSpecificCreatorMap() {

        return creatorRegistry.getClassAndAttributeSpecificCreatorMap();
    }
}
