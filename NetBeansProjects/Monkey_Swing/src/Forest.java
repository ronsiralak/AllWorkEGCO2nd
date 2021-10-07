
import java.util.*;

public class Forest {

    private Scanner sc = new Scanner(System.in);
     
    

    public void start() {
        ArrayList<Tree> ListTemp = new ArrayList<Tree>();
        Tree tree;
        int N = getnumberinput();
        int input;
        
        for (int i = 0; i < N; i++) {
            try {

                System.out.printf("T%d input height => ", i + 1);
                input = sc.nextInt();
                if (input < 0) {
                    throw new IllegalArgumentException();
                }
                ListTemp.add(new Tree("T" + String.valueOf(i + 1), input));

            } catch (Exception e) {

                System.out.println("input must be positive integer ");
                i--;
                sc.nextLine();
            }

        }

        Monkey Mk = new Monkey(ListTemp);
        Mk.Thinking();

    }

    public int getnumberinput() {

        int number;
        while (true) {
            try {
                System.out.print("Enter Number of Tree : ");
                number = sc.nextInt();
                if (number < 0) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (Exception e) {

                System.out.println("input must be positive integer ");
                sc.nextLine();
            }

        }
        return number;
    }

   /* public void bruteforce() {

        int data[] = {10, 5, 6, 3, 1, 8};
        int MIN, way = 0;
        boolean pass;
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                MIN = Math.min(data[i], data[j]);
                if (j - i == 1) {
                    way++;
                    System.out.printf("Way %d : %d to %d\n", way, data[i], data[j]);
                } else {
                    pass = true;
                    for (int k = i + 1; k <= j - 1; k++) {
                        if (data[k] >= MIN) {
                            pass = false;
                        }
                    }
                    if (pass) {
                        way++;
                        System.out.printf("Way %d : %d to %d\n", way, data[i], data[j]);
                    }
                }
            }
        }
        System.out.printf("Total path = %d\n",way);
    }*/

}
