package io;

import geometry.Rectangle;
import sprite.Block;

import java.awt.Color;
import java.awt.Image;
import java.util.List;

public class BlockFactoryImage implements BlockCreator{

    private int hitPoint;
    private int width;
    private int height;
    private List<Image> imageList;
    private Color stroke;

    public BlockFactoryImage(int hitPoint1, int width1, int height1, List<Image> imageList1, Color stroke1) {
        this.hitPoint = hitPoint1;
        this.width = width1;
        this.height = height1;
        this.imageList = imageList1;
        this.stroke = stroke1;


    }

    @Override
    public Block create(int xpos, int ypos) {
        geometry.Point point = new geometry.Point(xpos, ypos);
        geometry.Rectangle rec = new Rectangle(point, this.width, this.height);
        Block blockToCreate = new Block(rec, this.hitPoint, null, this.imageList, this.stroke);
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
