package menu;

/**
 * Select Option.
 *
 * @param <T> .
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class SelectOption<T> {

    private String key;
    private String message;
    private T returnVal;

    /**
     * Constructor.
     *
     * @param key1       .
     * @param message1   .
     * @param returnVal1 .
     */
    public SelectOption(String key1, String message1, T returnVal1) {
        this.key = key1;
        this.message = message1;
        this.returnVal = returnVal1;
    }

    /**
     * getter.
     *
     * @return String.
     */
    public String getKey() {
        return key;
    }

    /**
     * getter.
     *
     * @return message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * getter.
     *
     * @return return val.
     */
    public T getReturnVal() {
        return returnVal;
    }
}
