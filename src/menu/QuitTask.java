package menu;

import animation.AnimationRunner;

/**
 * QuitTask.
 *
 * @author Tomer Yona
 * @version 1.2 4 Apr 2019
 */
public class QuitTask implements Task<Void> {

    private AnimationRunner animationRunner;

    /**
     * Construnctor.
     *
     * @param ar .
     */
    public QuitTask(AnimationRunner ar) {
        this.animationRunner = ar;
    }

    @Override
    public Void run() {
        this.animationRunner.getGui().close();
        System.exit(0);
        return null;
    }
}
