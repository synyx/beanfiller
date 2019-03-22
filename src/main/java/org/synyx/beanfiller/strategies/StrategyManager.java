package org.synyx.beanfiller.strategies;

import org.synyx.beanfiller.domain.ObjectInformation;
import org.synyx.beanfiller.services.CreatorRegistry;

import java.util.Set;
import java.util.TreeSet;


/**
 * Manages the different Strategies.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class StrategyManager {

    private final Set<AbstractCreatorStrategy> strategies = new TreeSet<>();
    private final CreatorRegistry creatorRegistry;

    /**
     * Creates new StrategyMananger with the given CreatorRegistry and the default Strategies.
     *
     * @param  creatorRegistry  CreatorRegistry to use.
     */
    public StrategyManager(CreatorRegistry creatorRegistry) {

        this.creatorRegistry = creatorRegistry;
        addDefaultStrategies();
    }


    /**
     * Creates new StrategyMananger with the given CreatorRegistry and the given Strategies.
     *
     * @param  strategies  Strategies to use.
     * @param  creatorRegistry  CreatorRegistry to use.
     */
    public StrategyManager(Set<AbstractCreatorStrategy> strategies, CreatorRegistry creatorRegistry) {

        this.creatorRegistry = creatorRegistry;

        for (AbstractCreatorStrategy strategy : strategies) {
            addStrategyInternal(strategy);
        }
    }

    /**
     * Adds the given Strategies to the Strategies that are used.
     *
     * @param  strategy  the strategy to add.
     */
    public void addStrategy(AbstractCreatorStrategy strategy) {

        addStrategyInternal(strategy);
    }


    private void addStrategyInternal(AbstractCreatorStrategy strategy) {

        strategy.setStrategyManager(this);
        strategy.setCreatorRegistry(creatorRegistry);
        strategies.add(strategy);
    }


    /**
     * Tries to find a Strategy for the given object.
     *
     * @param  objectInformation  ObjectInformation to get the Strategy for.
     *
     * @return  a Strategy fit to create the Object, or null if none was found.
     */
    public AbstractCreatorStrategy getStrategyFor(ObjectInformation objectInformation) {

        for (AbstractCreatorStrategy strategy : strategies) {
            if (strategy.canHandle(objectInformation)) {
                return strategy;
            }
        }

        return null;
    }


    /**
     * @return  a copy of the currently set Strategies.
     */
    public Set<AbstractCreatorStrategy> getStrategies() {

        return new TreeSet<>(strategies);
    }


    private void addDefaultStrategies() {

        addStrategyInternal(new SimpleObjectStrategy());
        addStrategyInternal(new JustAnotherBeanStrategy());
        addStrategyInternal(new ArrayStrategy());
        addStrategyInternal(new CollectionStrategy());
        addStrategyInternal(new EnumStrategy());
        addStrategyInternal(new MapStrategy());
    }
}
