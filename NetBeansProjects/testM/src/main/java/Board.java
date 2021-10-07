
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class Board {
    // Fields that deals with the puzzle's logic

    private int size ;
    private int state ;
    private int[][] valueTable;
    private int numberOfClicks = 0;

    // boolean edit
    private boolean editMode = false;
    private int editOnOff = 0;

    public Board(int size, int state) {
        // Set puzzle's parameter
        this.size = size;
        this.state = state;
        // initialize comboBoxes for dropDown Panel
        String[] boardSizes = {" ", "3x3", "4x4", "5x5", "6x6", "7x7", "8x8"};
        String[] numOfColors = {"2 colors", "3 colors", "5 colors", "7 colors"};
        final int color = state;

    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void createBoard() {
        // Compute how unsolvable the current version of the puzzle is
        Solver percentSolvableCalculator = new Solver(size, size, state);
        percentSolvableCalculator.setBVector(new int[size * size]);
        percentSolvableCalculator.RowReduce();

        double percentSolvable = percentSolvableCalculator.percentSolvable() * 100;

        // initialize the values
        System.out.println("initialize");
        valueTable = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int times = 0; times < (int) (Math.random() * size); times++) {
                    //System.out.print("time: "+times+" ");
                    select(i, j); //random many times to click i,j

                }
                // System.out.print(valueTable[i][j] +" ");
            }
            // System.out.println();
        }
        System.out.println(percentSolvableCalculator.hasSolution());
    }

    public void select(int x, int y) {
        valueTable[x][y] = (valueTable[x][y] + 1) % state; // cycle the
        // selected
        if (editMode == false) {
            // tile
            if (x - 1 >= 0) { // cycle the left tile
                valueTable[x - 1][y] = (valueTable[x - 1][y] + 1) % state;
                // System.out.println("you have cycled " + (x-1) + " " + y);
            }
            if (x + 1 <= size - 1) { // cycle the right tile
                valueTable[x + 1][y] = (valueTable[x + 1][y] + 1) % state;
            }
            if (y + 1 <= size - 1) { // cycle the bottom tile
                valueTable[x][y + 1] = (valueTable[x][y + 1] + 1) % state;
            }
            if (y - 1 >= 0) { // cycle the top tile
                valueTable[x][y - 1] = (valueTable[x][y - 1] + 1) % state;
            }
        }
    }

    public void publishSolution() {
        // Create a solver
        Solver einstein = new Solver(size, size, state);

        // Build the b vector
        int[] b = new int[size * size];
        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                b[index] = state - valueTable[i][j];
                index++;
            }
        }
        //einstein.printA();
        // Give the b vector to the solver and solve
        einstein.setBVector(b);
        
        einstein.RowReduce();

        // Count the row of zero
        int zeroRow = einstein.zeroRowCount();
        double percentSolvable = 1 / Math.pow(state, zeroRow);

    }

    public String toString() {
        String output = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                output += valueTable[i][j] + " ";
            }
            output += "\n";
        }
        return output;
    }

    public int[] publishB() {
        int[] b = new int[size * size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                b[count] = (state - valueTable[i][j]) % state;
                count++;
            }
        }
        return b;
    }

    public int[][] solve() {
        return valueTable;
    }

    private boolean isSolved() {
        int n = valueTable[0][0]; // check the first tile
        for (int i = 0; i < size; i++) { // as soon as we encounter a tile of
            // different value
            for (int j = 0; j < size; j++) {
                if (valueTable[i][j] != n) {
                    return false; // return false
                }
            }
        }
        return true;
    }

}
