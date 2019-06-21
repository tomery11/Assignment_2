package io;

import background.ColorParser;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlocksDefinitionReader {
    public static Path path = null;

    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) throws IOException {
        List<String> lines = Files.readAllLines(BlocksDefinitionReader.path);
        HashMap<String, String> defaultMap = null;
        HashMap<String, String> blockBDefMap = null;
        HashMap<String, String> blockSDefMap = null;
        List<HashMap<String, String>> bdefMapList = new ArrayList<>();
        List<HashMap<String, String>> sdefMapList = new ArrayList<>();
        for (String str : lines) {
            if (str.equals(null)){
                continue;
            }
            if (str.contains("default") && !str.contains("#")) {
                str = str.substring(8);
                defaultMap = convertToMap(str);
            }


            if (str.contains("bdef")) {
                str = str.substring(5);
                blockBDefMap = convertToMap(str);
                bdefMapList.add(blockBDefMap);
            }

            if (str.contains("sdef")) {
                str = str.substring(5);
                blockSDefMap = convertToMap(str);
                sdefMapList.add(blockSDefMap);
            }
        }

        Map<String, Integer> spacerWidths = new HashMap<>();
        for (HashMap<String, String> sdef : sdefMapList) {
            spacerWidths.put(sdef.get("symbol"), Integer.parseInt(sdef.get("width")));
        }


        Map<String, BlockCreator> blockCreators = new HashMap<>();
        for (HashMap<String, String> bdef : bdefMapList) {
            String key = bdef.get("symbol");
            int width;
            if (bdef.containsKey("width")) {
                width = Integer.parseInt(bdef.get("width"));
            } else {
                width = Integer.parseInt(defaultMap.get("width"));
            }
            int height;
            if (bdef.containsKey("height")) {
                height = Integer.parseInt(bdef.get("height"));
            } else {
                height = Integer.parseInt(defaultMap.get("height"));
            }
            Color stroke;
            if (bdef.containsKey("stroke")) {
                stroke = ColorParser.colorFromString(bdef.get("stroke"));
            } else {
                if (defaultMap != null){
                    stroke = ColorParser.colorFromString(defaultMap.get("stroke"));
                }else {
                    stroke = Color.black;
                }

            }
            int hitPoint;
            if (bdef.containsKey("hit_points")) {
                hitPoint = Integer.parseInt(bdef.get("hit_points"));
            } else {
                hitPoint = Integer.parseInt(defaultMap.get("hit_points"));
            }


            int maxListSize = hitPoint;
            List<String> fillList = new ArrayList<>();
            if (bdef.containsKey("fill")) {
                fillList.add(bdef.get("fill"));
                for (int i = 1; i < maxListSize; i++) {
                    String fillCount = "fill-" + (i + 1);
                    if (bdef.containsKey(fillCount)) {
                        fillList.add(bdef.get(fillCount));
                    } else {
                        break;
                    }

                }
            } else {
                fillList.add(defaultMap.get("fill"));
                for (int i = 1; i < maxListSize; i++) {
                    String fillCount = "fill-" + (i + 1);
                    if (defaultMap.containsKey(fillCount)) {
                        fillList.add(defaultMap.get(fillCount));
                    } else {
                        break;
                    }

                }
            }
            BlockCreator blockCreatorToMap;
            List<Image> imageList = new ArrayList<>();
            List<Color> colorList = new ArrayList<>();
            if (fillList.get(0).contains("image")){
                for (String str : fillList){
                    str = str.substring(6,str.length() - 1);
                    BufferedImage img = null;
                    try {
                        img = ImageIO.read(new File(str));
                    } catch (IOException e) {
                    }
                    imageList.add(img);
                }
                blockCreatorToMap = new BlockFactoryImage(hitPoint, width, height,imageList,stroke);
            } else {
                for (String str : fillList){
                    str = str.substring(6,str.length() - 1);
                    colorList.add(ColorParser.colorFromString(str));
                }
                blockCreatorToMap = new BlockFactoryColor(hitPoint, width, height,colorList,stroke);
            }

            blockCreators.put(key,blockCreatorToMap);
        }



        BlocksFromSymbolsFactory toReturn = new BlocksFromSymbolsFactory(spacerWidths,blockCreators);

        return toReturn;
    }

    private static HashMap<String, String> convertToMap(String str) {
        HashMap<String, String> toReturn = new HashMap<>();

        //List<String> tempStringList = new ArrayList<>();
        String[] tokens = str.split(" ");
        for (String i : tokens) {
            String[] value = i.split(":");
            toReturn.put(value[0], value[1]);
        }

        return toReturn;
    }
}
