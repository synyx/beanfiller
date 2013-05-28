
package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.criteria.BigDecimalCriteria;

import java.math.BigDecimal;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class BigDecimalBuilder implements Builder<BigDecimal> {

    private BigDecimalCriteria criteria;

    public BigDecimalBuilder() {

        this(new BigDecimalCriteria());
    }


    public BigDecimalBuilder(BigDecimalCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public BigDecimal build() {

        BigDecimal min = criteria.getMin();
        BigDecimal max = criteria.getMax();

        BigDecimal range = max.subtract(min);
        BigDecimal result = min.add(range.multiply(new BigDecimal(Math.random())));

        return result;
    }
}
