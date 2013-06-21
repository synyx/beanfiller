
package org.synyx.beanfiller.criteria;

import java.math.BigDecimal;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class BigDecimalCriteria implements Criteria<BigDecimal> {

    private BigDecimal max;
    private BigDecimal min;

    public BigDecimalCriteria() {

        this(BigDecimal.ZERO, BigDecimal.valueOf(12.12));
    }


    public BigDecimalCriteria(BigDecimal min, BigDecimal max) {

        this.max = max;
        this.min = min;
    }

    public BigDecimal getMax() {

        return max;
    }


    public void setMax(BigDecimal max) {

        this.max = max;
    }


    public BigDecimal getMin() {

        return min;
    }


    public void setMin(BigDecimal min) {

        this.min = min;
    }
}
