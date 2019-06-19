package menu;

import animation.Animation;

public interface Menu<T> extends Animation {
    /**
     *
     * @param key String.
     * @param message String.
     * @param returnVal T .
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * @return status
     */
    T getStatus();
}
