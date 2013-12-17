
package org.synyx.beanfiller.criteria;

import java.util.Date;


/**
 * Criteria for Dates.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class DateCriteria implements Criteria<Date> {

    private static final long FIFTY_DAYS = 4320000000L;

    private Date minDate;
    private Date maxDate;

    /**
     * Creates new DateCriteria with the default values.
     */
    public DateCriteria() {

        long current = System.currentTimeMillis();

        minDate = new Date(current - FIFTY_DAYS);
        maxDate = new Date(current + FIFTY_DAYS);
    }


    /**
     * Creates new DateCriteria with the given values.
     *
     * @param  minDate  the minimum Date to use.
     * @param  maxDate  the maximum Date to use.
     */
    public DateCriteria(Date minDate, Date maxDate) {

        this.minDate = new Date(minDate.getTime());
        this.maxDate = new Date(maxDate.getTime());
    }

    /**
     * @return  the minimum Date.
     */
    public Date getMinDate() {

        return new Date(minDate.getTime());
    }


    /**
     * @param  minDate  the minimum Date.
     */
    public void setMinDate(Date minDate) {

        this.minDate = new Date(minDate.getTime());
    }


    /**
     * @return  the maximum Date.
     */
    public Date getMaxDate() {

        return new Date(maxDate.getTime());
    }


    /**
     * @param  maxDate  the maximum Date.
     */
    public void setMaxDate(Date maxDate) {

        this.maxDate = new Date(maxDate.getTime());
    }
}
