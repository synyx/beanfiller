
package org.synyx.beanfiller.criteria;

/**
 * Criteria for Strings.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class StringCriteria implements Criteria<String> {

    private int minlength;
    private int maxlength;
    private String charset;

    /**
     * init with default values.
     */
    public StringCriteria() {

        this(6, 8, "abcABCD0123äß!?");
    }


    /**
     * @param  minlength  minimum length of the string
     * @param  maxlength  maximum length of the string
     * @param  charset  charset from where the characters for the String are randomly picked.
     */
    public StringCriteria(int minlength, int maxlength, String charset) {

        this.minlength = minlength;
        this.maxlength = maxlength;
        this.charset = charset;
    }

    public int getMinlength() {

        return minlength;
    }


    public void setMinlength(int minlength) {

        this.minlength = minlength;
    }


    public int getMaxlength() {

        return maxlength;
    }


    public void setMaxlength(int maxlength) {

        this.maxlength = maxlength;
    }


    public String getCharset() {

        return charset;
    }


    /**
     * set the charset from where the characters for the String are randomly picked.
     *
     * @param  charset
     */
    public void setCharset(String charset) {

        this.charset = charset;
    }
}
