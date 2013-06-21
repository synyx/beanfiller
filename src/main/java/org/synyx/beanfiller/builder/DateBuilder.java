
package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.criteria.DateCriteria;
import org.synyx.beanfiller.services.RandomGenerator;

import java.util.Date;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class DateBuilder implements Builder<Date> {

    private DateCriteria criteria;

    public DateBuilder() {

        this.criteria = new DateCriteria();
    }


    public DateBuilder(DateCriteria dateCriteria) {

        this.criteria = dateCriteria;
    }

    @Override
    public Date build() {

        // just use the long values for getting a random Date
        return new Date(Math.round(
                    RandomGenerator.getRandomDouble() * (criteria.getMaxDate().getTime()
                        - criteria.getMinDate().getTime()) + criteria.getMinDate().getTime()));
    }
}
