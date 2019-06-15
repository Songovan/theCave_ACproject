package org.academiadecodigo.batmancave.maze;

public class Maze {

    private Cell[][] layout;

    private Excavator excavator;

    public Maze(int cols, int rows) {
        layout = new Cell[cols][rows];
        excavator = new Excavator(layout);
    }

    public void init() {

        // This method fills the layout with cells of type WALL or ROOM based in some rules to start excavating the maze


        for(int i = 0; i < layout.length; i++) {

            for(int j = 0; j < layout[i].length; j++) {

               if(i == 0 || i == layout.length - 1) {

                   // EDGES OF MAZE ARE WALLS

                   layout[i][j] = new Cell(CellType.WALL);

               } else if(i % 2 == 0) {

                   // EVEN COLUMNS ARE WALLS

                   layout[i][j] = new Cell(CellType.WALL);

               } else if(j == 0 || j == layout[i].length) {

                    // EDGES ARE WALLS

                   layout[i][j] = new Cell(CellType.WALL);

               } else if(j % 2 == 0) {

                   // EVEN ROWS ARE WALLS

                   layout[i][j] = new Cell(CellType.WALL);

               } else {

                   // ALL ELSE IS A ROOM

                   layout[i][j] = new Cell(CellType.ROOM);

               }
            }
        }
    }


    public void generate() {



        int[] start = {1,1};
        int[] nextMove = new int[2];
        excavator.getStack().empty();
        excavator.getStack().push(start);

        while(excavator.getStack().size() > 0) {

            //printMaze();

            nextMove = excavator.move();

            if(nextMove[0] == 0 && nextMove[1] == 0) {
                excavator.getStack().pop();
            } else {
                excavator.getStack().push(nextMove);
            }

            excavator.addStep();

            if (excavator.getNumSteps() > 2000) {
                break;
            }

        }



    }


    // FOR TESTING!!!!!!
    public void printMaze() {

        int maxX = layout.length;

        int maxY = layout[0].length;



        for (int j = 0; j < maxY; j++) {

            String line = "";

            for (int i = 0; i < maxX; i++) {

                if(layout[i][j].getType() == CellType.WALL) {

                    line += "█";

                } else {

                    line += " ";

                }

            }

            System.out.println(line);

        }



    }


    public Cell[][] getLayout() {
        return layout;
    }
}
