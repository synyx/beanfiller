package org.synyx.beanfiller.criteria;

/**
 * @author  Tobias Knell - knell@synyx.de.
 */
public class CharCriteria implements Criteria<Character> {

    /**
     * Charset to choose characters from.
     */
    private String charset;

    /**
     * Create a new CharCriteria using the default values.
     */
    public CharCriteria() {

        this("abcABCD0123äß!?");
    }


    /**
     * @param  charset  the charset to choose a character from
     */
    public CharCriteria(String charset) {

        this.charset = charset;
    }

    public String getCharset() {

        return charset;
    }


    public void setCharset(String charset) {

        this.charset = charset;
    }
}
