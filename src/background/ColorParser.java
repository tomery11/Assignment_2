package background;

import java.awt.Color;

/**
 * Color Parser.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class ColorParser {
    /**
     * parse color definition.
     *
     * @param s string of color
     * @return the specified color.
     */
    public static java.awt.Color colorFromString(String s) {
        if (s.startsWith("color(") || s.startsWith("RGB(")) {
            if (s.startsWith("color(")) {
                s = s.substring(6);
                s = s.replace(")", "");
            } else {
                s = s.substring(4);
                s = s.replace(")", "");
                String[] c = s.split(",");
                if (c.length != 3) {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (c.length == 3) {
                    return new Color(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2]));
                }
            }

        }
        s = s.replace(")", "");
        if (s.equals("blue")) {
            return Color.blue;
        } else if (s.equals("black")) {
            return Color.black;
        } else if (s.equals("cyan")) {
            return Color.cyan;
        } else if (s.equals("gray")) {
            return Color.gray;
        } else if (s.equals("lightGray")) {
            return Color.lightGray;
        } else if (s.equals("green")) {
            return Color.green;
        } else if (s.equals("orange")) {
            return Color.orange;
        } else if (s.equals("pink")) {
            return Color.pink;
        } else if (s.equals("red")) {
            return Color.red;
        } else if (s.equals("white")) {
            return Color.white;
        } else if (s.equals("yellow")) {
            return Color.yellow;
        }


        return null;
    }
}
