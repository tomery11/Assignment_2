package menu;

/**
 * interface Task.
 *
 * @param <T> .
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public interface Task<T> {
    /**
     * run.
     *
     * @return T.
     */
    T run();
}
