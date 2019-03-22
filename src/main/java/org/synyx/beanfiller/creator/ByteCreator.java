package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.util.RandomGenerator;


/**
 * Creator for Bytes.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ByteCreator implements SimpleCreator<Byte> {

    private final RandomGenerator randomGenerator;

    public ByteCreator(RandomGenerator randomGenerator) {

        this.randomGenerator = randomGenerator;
    }

    @Override
    public Byte create() {

        byte[] bytes = new byte[1];

        bytes = randomGenerator.getRandomBytes(bytes);

        return bytes[0];
    }
}
