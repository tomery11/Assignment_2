package menu;

public class SelectOption<T> {

    private String key;
    private String message;
    private T returnVal;


    public SelectOption(String key1, String message1, T returnVal1) {
        this.key = key1;
        this.message = message1;
        this.returnVal = returnVal1;
    }


    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }

    public T getReturnVal() {
        return returnVal;
    }
}
