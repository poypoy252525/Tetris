package tetris;

import tetris.tetrisblocks.*;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {

    //The tile size of every cell of the grid. 20 pixel
    public static final int TILE_SIZE = 20;
    //Numbers of row in the grid
    public static final int ROWS = 18;
    //Numbers of column in the grid
    public static final int COLUMNS = 10;
    //The size of the panel
    public static final int WIDTH = TILE_SIZE * COLUMNS;
    public static final int HEIGHT = TILE_SIZE * ROWS;

    private TetrisBlock[] blocks = {
            new TShape(),
            new OShape(),
            new IShape(),
            new SShape(),
            new ZShape(),
            new LShape(),
            new JShape()
    };

    //Declared TetrisBlock
    private TetrisBlock block;
    private Color[][] background;
    private int current, past;

    //constructor
    public GamePanel() {
        //Setting up the size of panel
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.white);
        this.setBorder(new LineBorder(Color.black));
        background = new Color[ROWS][COLUMNS];
    }

    //tetris block spawner
    public void spawnTetrisBlock() {
        Random r = new Random();
        current = r.nextInt(blocks.length);
        while (current == past) {
            current = r.nextInt(blocks.length);
        }
        block = blocks[current];
        block.spawn(COLUMNS);
        past = current;
    }

    //draw a block
    private void drawBlock(Graphics g, Color color, int x, int y) {
        //this line saying that when we draw something,
        //it should be colored exactly what the color value is set.
        g.setColor(color);
        //the fillRect method is responsible
        //for drawing and filling a rectangle
        g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        //now we set back the color to black
        g.setColor(Color.black);
        //draw the rectangle without its color
        g.drawRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public void drawTetrisBlock(Graphics g) {
        //it will loop until the row of the block is reach
        for (int row = 0; row < block.getHeight(); row++) {
            //it will loop until the column of the block is reach
            for (int col = 0; col < block.getWidth(); col++) {
                //check if the value of number in specific row and column is 1,
                //and if it's 1 we should draw a block
                if (block.getShape()[row][col] == 1) {
                    int x = block.getX() + col;
                    int y = block.getY() + row;
                    drawBlock(g, block.getColor(), x, y);
                }
            }
        }
    }

    public void moveTetrisBlockDown() {
        block.moveDown();
        repaint();
    }

    public void moveTetrisBlockLeft() {
        //return if the block is hit the left side
        if (hasObjectLeft()) return;
        block.moveLeft();
        repaint();
    }

    public void moveTetrisBlockRight() {
        //return the method if the block hits the right side panel
        if (hasObjectRight()) return;
        block.moveRight();
        repaint();
    }

    public void rotateTetrisBlock() {
        block.rotate();
        if (block.getLeftEdge() < 0) block.setX(0);
        if (block.getRightEdge() > COLUMNS) block.setX(COLUMNS - block.getWidth());
        if (block.getBottomEdge() > ROWS) block.setY(ROWS - block.getHeight());
        repaint();
    }

    public void dropTetrisBLock() {
        //while there is nothing below the block then keeps move down
        while (!hasObjectDown()) {
            block.moveDown();
        }
        repaint();
    }

    //checking if the tetris block hits something from its bottom edge.
    public boolean hasObjectDown() {
        //if the bottom is equal to the number of rows
        //it means it hits the ground
        if (block.getBottomEdge() == ROWS) {
            //we will return true
            return true;
        }

        for (int row = 0; row < block.getHeight(); row++) {
            for (int col = 0; col < block.getWidth(); col++) {
                if (block.getShape()[row][col] == 1) {
                    int x = block.getX() + col;
                    int y = block.getY() + row + 1;
                    //check if the y is less than 0 it will avoid outOfBoundsException
                    if (y < 0) break;
                    // if the background from the bottom of the current block is not null,
                    //that means there is a color and will return true
                    if (background[y][x] != null) return true;
                }
            }
        }

        //if anything of that is not true then we will return false
        return false;
    }

    public boolean hasObjectRight() {
        if (block.getRightEdge() == COLUMNS) return true;

        for (int row = 0; row < block.getHeight(); row++) {
            for (int col = 0; col < block.getWidth(); col++) {
                if (block.getShape()[row][col] == 1) {
                    int x = block.getX() + col + 1;
                    int y = block.getY() + row;
                    //check if the y is less than 0 it will avoid outOfBoundsException
                    if (y < 0) break;
                    // if the background from the bottom of the current block is not null,
                    //that means there is a color and will return true
                    if (background[y][x] != null) return true;
                }
            }
        }

        return false;
    }

    public boolean hasObjectLeft() {
        if (block.getLeftEdge() == 0) return true;

        for (int row = 0; row < block.getHeight(); row++) {
            for (int col = 0; col < block.getWidth(); col++) {
                if (block.getShape()[row][col] == 1) {
                    int x = block.getX() + col - 1;
                    int y = block.getY() + row;
                    //check if the y is less than 0 it will avoid outOfBoundsException
                    if (y < 0) break;
                    // if the background from the bottom of the current block is not null,
                    //that means there is a color and will return true
                    if (background[y][x] != null) return true;
                }
            }
        }

        return false;
    }

    private void drawBackground(Graphics g) {
        //iterate the background array
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                Color color = background[row][col];
                //checking if the background is not null,
                //if it's not then we need to draw that color
                if (color != null) {
                    //draw the block
                    drawBlock(g, color, col, row);
                }
            }
        }
    }

    public void moveToBackground() {
        //iterate the block shape array
        for (int row = 0; row < block.getHeight(); row++) {
            for (int col = 0; col < block.getWidth(); col++) {
                //check if the array value is 1
                if (block.getShape()[row][col] == 1) {
                    int x = block.getX() + col;
                    int y = block.getY() + row;
                    //give the background array's value to the block color in that way,
                    //the draw background will draw this.
                    background[y][x] = block.getColor();
                }
            }
        }
    }

    //clearing the line based on the argument that passed it.
    public void clearLine(int row) {
        //iterate every column of a row.
        for (int col = 0; col < COLUMNS; col++) {
            //make the background to null,
            //this will represent as clearing.
            background[row][col] = null;
        }
        //Copying the blocks from the row that cleared up to the top of it
        for (int r = row; r >= 0; r--) {
            for (int c = 0; c < COLUMNS; c++) {
                //We need to check if the row is not less than zero
                //to avoid getting out of bounds exception
                if (r <= 0) break;
                //copy the block on the top side.
                background[r][c] = background[r - 1][c];
            }
        }
    }

    //inside this method is where we check if the line is completely filled.
    public void clearLines() {
        //indicate if the filled block is equal to the number of columns, and if it is,
        //then we should clear that line.
        int filledBlock = 0;
        //iterate the grid panel
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                //if the block has a value.
                if (background[row][col] != null) {
                    filledBlock++;
                }
            }
            //if block that filled is equal to the columns it means it is completely filled
            if (filledBlock == COLUMNS) {
                //clear the line that detected
                clearLine(row);
            }
            //resetting the value to zero.
            filledBlock = 0;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawTetrisBlock(g);
    }
}
