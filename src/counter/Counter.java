package counter;

/**
 * Class that describes a counter.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class Counter {
    private int count;

    /**
     * constructor of counter, inits the counter to 0.
     * not sure if should be set to zero in constructor.
     *  @param currentCount .
     */
    public Counter(int currentCount) {
        this.count = currentCount;
    }


    /**
     * add number to current count.
     *
     * @param number .
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * subtract number to current count.
     *
     * @param number .
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }


    /**
     * return the value of the current count.
     *
     * @return int .
     */
    public int getValue() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
