package org.synyx.beanfiller.strategies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.synyx.beanfiller.domain.ObjectInformation;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.services.BeanAnalyzer;
import org.synyx.beanfiller.services.BeanSetter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Strategy that handles the leftovers - it has the lowest priority to ensure every other Strategy gets it chance first.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class JustAnotherBeanStrategy extends AbstractCreatorStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(JustAnotherBeanStrategy.class);

    /**
     * Creates new JustAnotherBeanStrategy.
     */
    public JustAnotherBeanStrategy() {

        // if no other Strategy fits, we assume this is another Bean that we need to create.
        super(0);
    }

    @Override
    public boolean canHandle(ObjectInformation objectInformation) {

        // we should be able to handle everything that is left over
        return true;
    }


    @Override
    public Object createObjectInternal(ObjectInformation parentInformation) throws FillingException {

        Class parentClazz = parentInformation.getClazz();

        // TODO handle non default constructors!
        try {
            Object instance = parentClazz.newInstance();

            List<ObjectInformation> objectInformationList = BeanAnalyzer.analyzeBean(parentClazz);
            Map<String, Object> createdObjectMap = new HashMap<String, Object>(objectInformationList.size());

            for (ObjectInformation information : objectInformationList) {
                information.setParent(parentInformation);

                AbstractCreatorStrategy strategy = getStrategyManager().getStrategyFor(information);
                Object o = strategy.createObject(information);

                if (o != null) {
                    createdObjectMap.put(information.getPath(), o);
                }
            }

            return BeanSetter.setBean(instance, objectInformationList, createdObjectMap);
        } catch (InstantiationException ex) {
            throw new FillingException("There was no Creator set for the class " + parentClazz.getName() + " (field '"
                + parentInformation.getField().getName() + "' of class " + parentClazz.getDeclaringClass() + "). "
                + " So we tried to instantiate it with the default constructor, but it failed! ", ex);
        } catch (IllegalAccessException ex) {
            throw new FillingException("There was no Creator set for the class " + parentClazz.getName()
                + " So we tried to instantiate it with the default constructor, but couldn't access it!", ex);
        }
    }
}
