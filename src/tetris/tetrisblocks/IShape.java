package tetris.tetrisblocks;

import tetris.TetrisBlock;

import java.awt.*;

public class IShape extends TetrisBlock {

    public IShape() {
        super(new int[][]{
                {1, 1, 1, 1}
        }, Color.cyan);
    }

    public void rotate() {
        super.rotate();
        if (getWidth() == 1) {
            setX(getX() + 1);
            setY(getY() - 1);
        } else {
            setX(getX() - 1);
            setY(getY() + 1);
        }
    }
}
