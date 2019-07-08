package org.academiadecodigo.batmancave.maze;

import java.util.Stack;

public class Excavator {

    private Stack<int[]> stack;
    private Cell[][] layout;
    private int numSteps;
    private final int[] noMove = {0,0};

    public Excavator(Cell[][] layout) {
        stack = new Stack<>();
        this.layout = layout;
        numSteps = 0;
    }


    private Directions getDirectionToMove(boolean[] availableRooms) {

        int col = stack.get(stack.size()-1)[0];
        int row = stack.get(stack.size()-1)[1];

        // UP
        if(row - 2 > 0 &&
                layout[col][row - 2].getType() == CellType.ROOM &&
                !layout[col][row - 2].isExcavated()) {
            availableRooms[0] = true; // Room is available
        }

        // RIGHT
        if(col + 2 < layout.length &&
                layout[col + 2][row].getType() == CellType.ROOM &&
                !layout[col + 2][row].isExcavated()) {
            availableRooms[1] = true; // Room is available
        }

        // DOWN
        if(row + 2 < layout[0].length &&
                layout[col][row + 2].getType() == CellType.ROOM &&
                !layout[col][row + 2].isExcavated()) {
            availableRooms[2] = true; // Room is available
        }

        // LEFT
        if(col - 2 > 0 &&
                layout[col - 2][row].getType() == CellType.ROOM &&
                !layout[col - 2][row].isExcavated()) {
            availableRooms[3] = true; // Room is available
        }

        return RandomRoom.randomRoom(availableRooms);
    }

    public int[] move() {

        // Get current position stored in stack

        int col = stack.get(stack.size()-1)[0];
        int row = stack.get(stack.size()-1)[1];

        layout[col][row].excavate();

        boolean[] possibleRooms = {false, false, false, false}; // UP - RIGHT - DOWN - LEFT

        Directions move = getDirectionToMove(possibleRooms);

        if (move == null) {
            return noMove;
        }

        int nextCol = 0;
        int nextRow = 0;

        switch (move) {
            case UP:
                nextCol = 0;
                nextRow = -2;
                layout[col][row - 1].setType(CellType.ROOM);
                layout[col][row - 1].excavate();
                break;
            case RIGHT:
                nextCol = 2;
                nextRow = 0;
                layout[col + 1][row].setType(CellType.ROOM);
                layout[col + 1][row].excavate();
                break;
            case DOWN:
                nextCol = 0;
                nextRow = 2;
                layout[col][row + 1].setType(CellType.ROOM);
                layout[col][row + 1].excavate();
                break;
            case LEFT:
                nextCol = -2;
                nextRow = 0;
                layout[col - 1][row].setType(CellType.ROOM);
                layout[col - 1][row].excavate();
                break;
            default:
                nextCol = 0;
                nextRow = 0;
                break;
        }

        int totalAvailableRooms = countAvailableRooms(possibleRooms);

        /*
        for(int i = 0; i < availableRooms.length; i++) {
            if(availableRooms[i]) {
                totalAvailableRooms++;
            }
        }

         */

        int[] moveTo = {0, 0};

        if(totalAvailableRooms > 4 || totalAvailableRooms < 1) {
            return moveTo;
        } else {
            moveTo[0] = col + nextCol;
            moveTo[1] = row + nextRow;
            return moveTo;
        }

    }

    public int countAvailableRooms(boolean[] arr) {

        int roomCounter = 0;

        for(int i = 0; i < arr.length; i++) {
            if(arr[i]) {
                roomCounter++;
            }
        }

        return roomCounter;
    }

    public Stack getStack() {
        return stack;
    }

    public int getNumSteps() {
        return numSteps;
    }

    public void addStep() {
        numSteps++;
    }
}
