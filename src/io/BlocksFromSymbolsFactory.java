package io;

import sprite.Block;

import java.util.Map;

public class BlocksFromSymbolsFactory {

    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    public BlocksFromSymbolsFactory(Map<String, Integer> spaceMap, Map<String, BlockCreator> blockMap) {
        this.spacerWidths = spaceMap;
        this.blockCreators = blockMap;
    }


    /**
     * returns true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * returns true if 's' is a valid block symbol.
     * @param s
     * @return
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }



    /**
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     * @param s
     * @param xpos
     * @param ypos
     * @return
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos,ypos);
    }



    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     * @param s
     * @return
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }


}
