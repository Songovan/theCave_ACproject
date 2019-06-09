package org.academiadecodigo.batmancave.maze;

import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

public class Excavator {

    private Stack stack;
    private Cell[][] layout;
    private int numSteps;

    public Excavator(Cell[][] layout) {
        stack = new Stack();
        this.layout = layout;
        numSteps = 0;
    }



    public int[] move() {

        // Get current position stored in stack
        System.out.println(stack.size());

        int col = ((int[])stack.get(stack.size()-1))[0];
        int row = ((int[])stack.get(stack.size()-1))[1];

        layout[col][row].getType().isExcavated();

        boolean[] availableRooms = {false, false, false, false}; // UP - RIGHT - DOWN - LEFT

        // UP
        if(row - 2 > 0 &&
                layout[col][row - 2].getType() == CellType.ROOM &&
                !layout[col][row - 2].getType().getExcavated()) {
            availableRooms[0] = true; // Room is available
        }

        // RIGHT
        if(col + 2 > layout.length &&
                layout[col + 2][row].getType() == CellType.ROOM &&
                !layout[col + 2][row].getType().getExcavated()) {
            availableRooms[1] = true; // Room is available
        }

        // DOWN
        if(row + 2 > layout[0].length &&
                layout[col][row + 2].getType() == CellType.ROOM &&
                !layout[col][row + 2].getType().getExcavated()) {
            availableRooms[2] = true; // Room is available
        }

        // LEFT
        if(col - 2 > 0 &&
                layout[col - 2][row].getType() == CellType.ROOM &&
                !layout[col - 2][row].getType().getExcavated()) {
            availableRooms[3] = true; // Room is available
        }


        Directions move = randomRoom(availableRooms);

        int nextCol = 0;
        int nextRow = 0;

        switch (move) {
            case UP:
                nextCol = 0;
                nextRow = -2;
                layout[col][row - 1].setType(CellType.ROOM);
                layout[col][row - 1].getType().isExcavated();
                break;
            case RIGHT:
                nextCol = 2;
                nextRow = 0;
                layout[col + 1][row].setType(CellType.ROOM);
                layout[col + 1][row].getType().isExcavated();
                break;
            case DOWN:
                nextCol = 0;
                nextRow = 2;
                layout[col][row + 1].setType(CellType.ROOM);
                layout[col][row + 1].getType().isExcavated();
                break;
            case LEFT:
                nextCol = -2;
                nextRow = 0;
                layout[col - 1][row].setType(CellType.ROOM);
                layout[col - 1][row].getType().isExcavated();
                break;
            default:
                nextCol = 0;
                nextRow = 0;
                break;
        }

        int totalAvailableRooms = 0;

        for(int i = 0; i < availableRooms.length; i++) {
            if(availableRooms[i]) {
                totalAvailableRooms++;
            }
        }

        int[] moveTo = {0, 0};

        if(totalAvailableRooms > 4 || totalAvailableRooms < 1) {
            return moveTo;
        } else {
            moveTo[0] = col + nextCol;
            moveTo[1] = row + nextRow;
            return moveTo;
        }



    }

    private Directions randomRoom(boolean[] arr) {

        int roomCounter = 0;

        for(int i = 0; i < arr.length; i++) {
            if(arr[i]) {
                roomCounter++;
            }
        }

        for(boolean bool: arr) {
            System.out.println(bool);
        }

        List helper = new ArrayList();

        for(int i = 0; i < arr.length; i++) {
            if(arr[i]) {
                helper.add(Directions.values()[i]);
            }
        }

        return (Directions) helper.get((int)Math.floor(Math.random() * helper.size()));

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
