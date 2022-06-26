package tetris;

public class GameThread extends Thread {

    private GamePanel panel;

    //constructor
    public GameThread(GamePanel panel) {
        this.panel = panel;
    }


    @Override
    public void run() {
        //this is the main loop we will set it to true to make it infinite
        while (true) {
            //spawn the block if the other is hit
            panel.spawnTetrisBlock();
            //while current object does not hit other pieces or the ground
            while (!panel.hasObjectDown()) {
                //move the current tetris piece down
                panel.moveTetrisBlockDown();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            //move down because the block can't go down
            panel.moveToBackground();
            panel.clearLines();
        }
    }
}
