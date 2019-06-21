package io;

import levels.LevelInformation;
import menu.SelectOption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LevelSetReader {
    private java.nio.file.Path path;
    private HashMap<String, List<String>> mapToAdd;
    //private List<SelectOption> optionList;

    public LevelSetReader(Path myPath) {
        this.path = myPath;
        this.mapToAdd = new HashMap<>();
        //this.optionList = new ArrayList<>();

    }


    public void fromReader(java.io.Reader reader) throws IOException {
        List<LevelSpecificationReader> levelList = new ArrayList<>();
        //List<HashMap<String, String>> mapList = new ArrayList<>();
        //HashMap<String, List<String>> mapToAdd = null;

        if (this.path == null) {
            throw new NoSuchFileException("file is null");
        }
        List<String> lines = Files.readAllLines(this.path);
        for (int i = 0; i < lines.size(); i+=2) {
            String str = lines.get(i);
            String[] arr = new String[2];
            List<String> listInMap = new ArrayList<>();
            if (str.contains(":")) {

                arr = str.split(":", 2);
                listInMap.add(arr[1]);
                //this.optionList.add(new SelectOption(arr[0],arr[1],))
            }
            if (i + 1 < lines.size()){
                listInMap.add(lines.get(i + 1));
                this.mapToAdd.put(arr[0], listInMap);
            }

        }





        //return levelList;
    }


    public HashMap<String, List<String>> getMapToAdd() {
        return mapToAdd;
    }
}
