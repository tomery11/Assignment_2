package menu;

import animation.AnimationRunner;

public class QuitTask implements Task<Void> {

    AnimationRunner animationRunner;

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
