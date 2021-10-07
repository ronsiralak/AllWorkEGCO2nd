package lightsOutGraph.graphdata;

public class Matrix {

    private int numStates;
    private int[][] matrix;
    private int[][] inverse;
    private int rank;
    private int[][] quiet;

    public Matrix(int size, int ns) {
        reset(size, ns);
    }

    public void reset(int size, int ns) {
        if (matrix == null || matrix.length != size) {
            matrix = new int[size][size];
        } else {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        numStates = ns;
        inverse = null;
        quiet = null;
    }

    public void set(int row, int col, int val) {
        matrix[row][col] = val;
        inverse = null;
    }

    public void calculateInverse() {
        if (inverse != null) {
            return;
        }
        int size = matrix.length;
        inverse = new int[size][size + size];

        // initialise inverse
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                inverse[i][j] = matrix[i][j];
                inverse[i][j + size] = 0;
            }
            inverse[i][i + size] = 1;
        }

        // calculate inverse
        int row = 0;
        for (int col = 0; col < size; col++) {
            // find a remaining row with entry in that column
            int row2 = row;
            while (row2 < size && inverse[row2][col] == 0) {
                row2++;
            }
            if (row2 < size) {
                // move row up to first available unsolved row
                swapRows(inverse, row2, row);

                // reduce any other remaining row with entry in that column
                for (row2 = row + 1; row2 < size; row2++) {
                    while (inverse[row2][col] != 0) {
                        // reduce row2 to zero in that column
                        if (inverse[row][col] > inverse[row2][col]) {
                            swapRows(inverse, row, row2);
                        }
                        int f = inverse[row2][col] / inverse[row][col];
                        subtractRows(inverse, row, row2, f);
                    }
                }
                // divide row to make its leading entry 1 (or as close as possible)
                multiplyRow(inverse, row, reciprocal(inverse[row][col]));

                // use row to reduce upper part of column
                for (row2 = 0; row2 < row; row2++) {
                    // reduce row2 to zero in that column
                    subtractRows(inverse, row, row2, inverse[row2][col] / inverse[row][col]);
                }
                // next row
                row++;
            }
        }
        rank = row;

        // extract quiet patterns
        quiet = new int[size - rank][size];
        for (int row2 = row; row2 < size; row2++) {
            for (int col = 0; col < size; col++) {
                quiet[row2 - row][col] = inverse[row2][col + size];
                inverse[row2][col + size] = 0;
            }
        }

        // rearrange rows so leading entries on diagonal
        for (int row2 = row - 1; row2 >= 0; row2--) {
            for (int col = 0; col < size; col++) {
                if (inverse[row2][col] != 0) {
                    swapRows(inverse, row2, col);
                    break;
                }
            }
        }

        // reduce size the quiet patterns
        boolean improved = true;
        while (improved) {
            improved = false;
            for (int[] qp1 : quiet) {
                for (int[] qp2 : quiet) {
                    if (qp1 != qp2) {
                        int score = 0;
                        for (int i = 0; i < qp1.length; i++) {
                            if (qp1[i] + qp2[i] == numStates) {
                                score++;
                            } else if (qp1[i] == 0 && qp2[i] != 0) {
                                score--;
                            }
                        }
                        if (score > 0) {
                            for (int i = 0; i < qp1.length; i++) {
                                qp1[i] = (qp1[i] + qp2[i]) % numStates;
                            }
                            improved = true;
                        }
                    }
                }
            }
        }
    }

// row manipulation
    private void swapRows(int[][] mx, int r1, int r2) {
        for (int i = 0; i < mx[r1].length; i++) {
            int c = mx[r1][i];
            mx[r1][i] = mx[r2][i];
            mx[r2][i] = c;
        }
    }

    private void subtractRows(int[][] mx, int r1, int r2, int f) {
        if (f == 0) {
            return;
        }
        int f2 = -f;
        while (f2 < 0) {
            f2 += numStates;
        }
        for (int i = 0; i < mx[r1].length; i++) {
            mx[r2][i] += f2 * mx[r1][i];
            mx[r2][i] %= numStates;
        }
    }
    // return reciprocal of f modulo numStates.
    // Note: numStates is assumed to be a prime
    // Should really use extended gcd algorithm.

    private int reciprocal(int f) {
        if (f <= 1) {
            return f;
        }
        for (int i = 2; i < numStates; i++) {
            if ((f * i) % numStates == 1) {
                return i;
            }
        }
        return 1;
    }

    private void multiplyRow(int[][] mx, int r1, int f) {
        if (f == 1) {
            return;
        }
        for (int i = 0; i < mx[r1].length; i++) {
            mx[r1][i] *= f;
            mx[r1][i] %= numStates;
        }
    }

// vector multiplication
    public int[] getSolution(int[] state) {
        int size = matrix.length;
        if (state.length != size) {
            return null;
        }
        int[] solution = new int[state.length];
        calculateInverse();

        // do matrix multiplication inverse*state -> solution
        for (int row = 0; row < size; row++) {
            solution[row] = 0;
            for (int col = 0; col < size; col++) {
                solution[row] += inverse[row][col + size] * state[col];
            }
            solution[row] %= numStates;
        }

        // check if it really is a solution
        // by checking that matrix*solution=state
        for (int row = 0; row < size; row++) {
            int t = 0;
            for (int col = 0; col < size; col++) {
                t += matrix[row][col] * solution[col];
            }
            t %= numStates;
            if (t != state[row]) {
                return null;
            }
        }
        return solution;
    }

    public int[][] getQuiet() {
        return quiet;
    }

    public String toString() {
        String s = "";
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s += matrix[i][j];
            }
            if (inverse != null) {
                s += "  ";
                for (int j = 0; j < n; j++) {
                    s += inverse[i][j];
                }
                s += "  ";
                for (int j = 0; j < n; j++) {
                    s += inverse[i][j + n];
                }
            }
            s += "\n";
        }
        if (quiet != null) {
            s += "quiet patterns: " + quiet.length;
            for (int i = 0; i < quiet.length; i++) {
                s += "\n";
                for (int j = 0; j < n; j++) {
                    s += quiet[i][j];
                }
            }
        }
        s += "\n";
        return s;
    }
}
