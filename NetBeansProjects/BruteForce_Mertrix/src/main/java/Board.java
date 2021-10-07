
public class Board {
    // Fields that deals with the puzzle's logic

    private int size;
    private int[][] valueTable;

    public Board(int size) {
        // Set puzzle's parameter
        this.size = size;
        System.out.println("initialize");
        valueTable = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                valueTable[i][j] = 0;
            }
        }
    }

    public void click(int[][] graph) {
        for(int i=0;i<graph.length;i++){
             for(int j=0;j<graph.length;j++){
                 if(graph[i][j]==1){
                     select(i, j);
                 }
             }
        }
    }

    public void select(int x, int y) {
        valueTable[x][y] = (valueTable[x][y] + 1) % 2; // cycle the
        // selected
        // tile
        if (x - 1 >= 0) { // cycle the left tile
            valueTable[x - 1][y] = (valueTable[x - 1][y] + 1) % 2;
            // System.out.println("you have cycled " + (x-1) + " " + y);
        }
        if (x + 1 <= size - 1) { // cycle the right tile
            valueTable[x + 1][y] = (valueTable[x + 1][y] + 1) % 2;
        }
        if (y + 1 <= size - 1) { // cycle the bottom tile
            valueTable[x][y + 1] = (valueTable[x][y + 1] + 1) % 2;
        }
        if (y - 1 >= 0) { // cycle the top tile
            valueTable[x][y - 1] = (valueTable[x][y - 1] + 1) % 2;
        }
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

    public void show() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(valueTable[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void findall() {
    }
}
