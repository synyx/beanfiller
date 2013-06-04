
package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.criteria.DateCriteria;

import java.util.Date;
import java.util.Random;


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

        Random rand = new Random(System.currentTimeMillis());

        // just use the long values for getting a random Date
        return new Date(Math.round(
                    rand.nextDouble() * (criteria.getMaxDate().getTime() - criteria.getMinDate().getTime())
                    + criteria.getMinDate().getTime()));
    }
}
