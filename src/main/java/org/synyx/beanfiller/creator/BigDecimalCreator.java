
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.BigDecimalCriteria;
import org.synyx.beanfiller.services.RandomGenerator;

import java.math.BigDecimal;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class BigDecimalCreator implements SimpleCreator<BigDecimal> {

    private BigDecimalCriteria criteria;

    public BigDecimalCreator() {

        this(new BigDecimalCriteria());
    }


    public BigDecimalCreator(BigDecimalCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public BigDecimal create() {

        BigDecimal min = criteria.getMin();
        BigDecimal max = criteria.getMax();

        BigDecimal range = max.subtract(min);
        BigDecimal result = min.add(range.multiply(BigDecimal.valueOf(RandomGenerator.getRandomDouble())));

        return result;
    }
}
