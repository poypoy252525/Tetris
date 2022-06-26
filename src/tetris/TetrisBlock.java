package tetris;

import java.awt.*;

public class TetrisBlock {

    private int[][][] shapes;
    private int currentRotation;
    private int[][] shape;
    private Color color;
    private int x;
    private int y;

    public TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
        initShapes();
    }

    //initialize the shape rotation of the current shape
    private void initShapes() {
        //initialize the shapes array first we set it to 4
        //since all cases of tetris block rotating is four
        shapes = new int[4][][];
        //iterate all shape inside shapes
        for (int i = 0; i < shapes.length; i++) {
            //swapping width and height in every rotation
            int width = this.getHeight();
            int height = this.getWidth();
            //initialize the shape inside the shapes array
            shapes[i] = new int[height][width];
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    //assigning the value of the shape to the next orientation shape
                    shapes[i][row][col] = shape[width - col - 1][row];
                }
            }
            //now we change the shape value to the rotated for the next iteration
            shape = shapes[i];
        }
    }

    //rotate the tetris block
    public void rotate() {
        //increment the rotation index to change the shape
        currentRotation++;
        //check if the index is greater than 3,
        //because we don't want it to exceed the length array
        if (currentRotation > 3) currentRotation = 0;
        shape = shapes[currentRotation];
    }

    //spawn for tetris block
    public void spawn(int columns) {
        //initial orientation of the shape
        currentRotation = 3;
        shape = shapes[currentRotation];
        //the x coordinate (Middle)
        this.x = (columns / 2) - (this.getWidth() / 2);
        //the y coordinate (Top)
        this.y = 0;
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return shape[0].length;
    }

    public int getHeight() {
        return shape.length;
    }

    public void moveDown() {
        //we will increase y coordinate to move down
        y++;
    }

    public void moveRight() {
        //move right the x coordination
        x++;
    }

    public void moveLeft() {
        //move left the x coordination
        x--;
    }

    public int getBottomEdge() {
        return y + getHeight();
    }

    public int getLeftEdge() {
        return x;
    }

    public int getRightEdge() {
        return x + getWidth();
    }
}
