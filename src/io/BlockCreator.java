package io;

import sprite.Block;

/**
 * BlockCreator interface.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public interface BlockCreator {


    /**
     * Create a block at the specified location.
     *
     * @param xpos .
     * @param ypos .
     * @return Block
     */
    Block create(int xpos, int ypos);

    /**
     * getter.
     *
     * @return int .
     */
    int getWidth();

    /**
     * getter.
     *
     * @return int .
     */
    int getHeight();
}
