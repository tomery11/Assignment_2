package io;

import sprite.Block;

import java.awt.Color;
import java.util.List;

import geometry.Point;
import geometry.Rectangle;

/**
 * BlockCreator Factory.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class BlockFactoryColor implements BlockCreator {
    private int hitPoint;
    private int width;
    private int height;
    private List<Color> colorList;
    private Color stroke;

    /**
     * Constructor.
     *
     * @param hitPoint1  .
     * @param width1     .
     * @param height1    .
     * @param colorList1 .
     * @param stroke1    .
     */
    public BlockFactoryColor(int hitPoint1, int width1, int height1, List<Color> colorList1, Color stroke1) {
        this.hitPoint = hitPoint1;
        this.width = width1;
        this.height = height1;
        this.colorList = colorList1;
        this.stroke = stroke1;


    }


    @Override
    public Block create(int xpos, int ypos) {
        Point point = new Point(xpos, ypos);
        Rectangle rec = new Rectangle(point, this.width, this.height);
        Block blockToCreate = new Block(rec, this.hitPoint, this.colorList, null, this.stroke);
        return blockToCreate;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }
}
