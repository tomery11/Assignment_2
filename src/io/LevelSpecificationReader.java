package io;


import background.Background;
import collision.Velocity;
import geometry.Point;
import levels.LevelInformation;
import sprite.Block;
import sprite.Sprite;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static background.ColorParser.colorFromString;


public class LevelSpecificationReader {
    private java.nio.file.Path path;


    public LevelSpecificationReader(java.nio.file.Path myPath) {
        this.path = myPath;

    }

    public List<LevelInformation> fromReader(java.io.Reader reader) throws IOException {
        List<LevelInformation> levelList = new ArrayList<>();
        List<HashMap<String, String>> mapList = new ArrayList<>();
        HashMap<String, String> mapToAdd = null;
        List<List<String>> startBlockList = new ArrayList<>();
        List<String> stringList = null;

        boolean startBlockFlag = false;
        if (this.path == null) {
            throw new NoSuchFileException("file is null");
        }
        List<String> lines = Files.readAllLines(this.path);


        for (String str : lines) {
            if (str.equals("")){
                continue;
            }
            if (str.equals("START_LEVEL")) {
                mapToAdd = new HashMap<>();
            }
            if (str.contains(":")) {
                String[] arr = new String[2];
                arr = str.split(":", 2);
                mapToAdd.put(arr[0], arr[1]);
            } else {

                mapToAdd.put(str, "$");

                if (str.equals("END_LEVEL")) {
                    mapList.add(mapToAdd);
                }

            }
            if (str.equals("START_BLOCKS")) {
                startBlockFlag = true;
                stringList = new ArrayList<>();
                continue;

            }
            if (startBlockFlag) {
                if (str.equals("END_BLOCKS")){
                    startBlockFlag = false;
                    startBlockList.add(stringList);
                }else{
                    stringList.add(str);
                }
            }

        }
        LevelInfo7 levelToAdd;
        List<String> tempBlockList;
        List<Block> blockList;
        for (int i = 0; i < mapList.size(); i++) {
            int x = 30;
            int y = 40;
            blockList = new ArrayList<>();
            levelToAdd = new LevelInfo7();
            levelToAdd.setLevelName(mapList.get(i).get("level_name"));
            levelToAdd.setInitialBallVelocity(parseVelocity(mapList.get(i).get("ball_velocities")));
            levelToAdd.setGetBackground(backgroundConverter(mapList.get(i).get("background")));
            levelToAdd.setPaddleSpeed(Integer.parseInt(mapList.get(i).get("paddle_speed")));
            levelToAdd.setPaddleWidth(Integer.parseInt(mapList.get(i).get("paddle_width")));
            BlocksDefinitionReader.path = Paths.get(mapList.get(i).get("block_definitions"));
            BlocksFromSymbolsFactory blocksFromSymbolsFactory = BlocksDefinitionReader.fromReader(reader);
            tempBlockList = startBlockList.get(i);
            int row_height = Integer.parseInt(mapList.get(i).get("row_height"));
            int h = 0;
            for (String str : tempBlockList) {
                x = 30;
                for (int j = 0; j < str.length(); j++) {
                    String arg = ""+ str.charAt(j);
                    if (blocksFromSymbolsFactory.isBlockSymbol(arg)) {
                        Block tmp = blocksFromSymbolsFactory.getBlock(arg, x, y);
                        blockList.add(tmp);
                        x += tmp.getWidth();
                        h = tmp.getHeight();

                    } else {
                        x += blocksFromSymbolsFactory.getSpaceWidth(arg);
                        h = row_height;

                    }
                }
                y += h;
            }

            levelToAdd.setBlocks(blockList);
            levelToAdd.setNumberOfBlocksToRemove(blockList.size());
            levelToAdd.setNumberOfBalls(levelToAdd.initialBallVelocities().size());
            List<Point> ballLocation = new ArrayList<>();
            ballLocation.add(new Point(400,400));
            levelToAdd.setBallLocation(ballLocation);
            levelList.add(levelToAdd);

        }



        return levelList;

    }


    private List<Velocity> parseVelocity(String toParse) {
        List<Velocity> toReturn = new ArrayList<>();
        List<String> tempStringList = new ArrayList<>();
        String[] tokens = toParse.split(" ");
        for (String i : tokens) {
            String[] axis = i.split(",");
            double dx = Double.parseDouble(axis[0]);
            double dy = Double.parseDouble(axis[1]);
            Velocity velToAdd = new Velocity(dx, dy);
            toReturn.add(velToAdd);
        }


        return toReturn;
    }

    private Sprite backgroundConverter(String toConvert) {
        int index = toConvert.indexOf('(');
        Background toReturn;
        if (toConvert.contains("image")) {
            String path = toConvert.substring(index + 1, toConvert.length() - 1);
            //Image image = new ImageIcon(path).getImage();
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File(path));
            } catch (IOException e) {
            }
            toReturn = new Background(image);

        } else {
            String colorString = toConvert.substring(index + 1, toConvert.length() - 1);
            Color color = colorFromString(colorString);
            toReturn = new Background(color);

        }
        return toReturn;
    }

}
