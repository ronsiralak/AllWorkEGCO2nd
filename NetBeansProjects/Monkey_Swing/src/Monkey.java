
import java.util.*;

public class Monkey {

    
    private ArrayList<Tree> tree;
    private Scanner sc = new Scanner(System.in);

    public Monkey( ArrayList<Tree> t) {
        
        tree = t;
    }

    public void Swing() {
        Stack<Tree> stack = new Stack<Tree>();

        Tree temp;
        int numPairs = 0;
        for (int i = 0; i < tree.size(); i++) {

            if (stack.isEmpty()) {
                stack.push(tree.get(i));
            } else if (!stack.isEmpty()) {
                System.out.printf("Round %d \n", i);
                while (!stack.isEmpty() && tree.get(i).getheight() > stack.peek().getheight()) {

                    temp = stack.pop();

                    System.out.printf(" %s, %2d ft --> %2s %2d ft\n", temp.getname(), temp.getheight(), tree.get(i).getname(), tree.get(i).getheight());

                    numPairs++;
                }

                if (stack.isEmpty()) {
                    stack.push(tree.get(i));
                } else if (!stack.isEmpty() && tree.get(i).getheight() < stack.peek().getheight()) //all the other cases
                {
                    temp = stack.peek();

                    System.out.printf(" %s, %2d ft --> %2s %2d ft\n", temp.getname(), temp.getheight(), tree.get(i).getname(), tree.get(i).getheight());
                    stack.push(tree.get(i));
                    numPairs++;
                } else if (tree.get(i).getheight() == stack.peek().getheight()) //not pushed because they are same. just increment the count
                {
                    temp = stack.peek();

                    System.out.printf(" %s, %2d ft --> %2s %2d ft\n", temp.getname(), temp.getheight(), tree.get(i).getname(), tree.get(i).getheight());

                    numPairs++;
                }
            }
        }
        System.out.printf("total path = %d \n", numPairs);

    }

    public void Thinking() {

        int choice = 0;

        while (true) {
            System.out.printf("\nMonkey is thinking guide him what to do \n");
            System.out.println("1. Swing");
            System.out.println("2. Edit Tree");
            System.out.println("3. Exit");

            System.out.print("Enter your Activity => ");

            /* try {
                choice = sc.nextInt();
                if (choice < 0) {
                    throw new IllegalArgumentException();
                }

            } catch (Exception e) {
                System.out.println("Input must be positive Integer ");
                sc.nextLine();
                continue;
            }*/
            choice = getnumberinput();

            switch (choice) {
                case -1:
                    break;
                case 1:
                    Swing();
                    break;
                case 2:
                    Edit();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("wrong input enter again");

            }

        }

    }

    public void sortorderTree() {

        for (int i = 0; i < tree.size(); i++) {

            tree.get(i).setname("T" + String.valueOf(i + 1));
        }
    }

    public int getnumberinput() {

        int number = 0;

        try {
            // System.out.print("Enter your Activity ");
            number = sc.nextInt();
            if (number < 0) {
                throw new IllegalArgumentException();
            }

        } catch (Exception e) {

            System.out.println("input must be positive integer ");
            sc.nextLine();
            number = -1;
        }

        return number;
    }

    public void Edit() {

        int choice;
        int temp;
        while (true) {

            System.out.printf("\nEdit Tree \n");
            System.out.println("1. Tree");
            System.out.println("2. Add");
            System.out.println("3. Delete");
            System.out.println("4. Back");

           System.out.print("Enter your Activity => ");

            /* try {
                choice = sc.nextInt();
                if (choice < 0) {
                    throw new IllegalArgumentException();
                }

            } catch (Exception e) {
                System.out.println("Input must be positive Integer ");
                sc.nextLine();
                continue;
            }*/
            choice = getnumberinput();

            switch (choice) {
                case -1:
                    break;
                case 1:
                    printTree();
                    break;
                case 2:
                    AddTree();
                    break;
                case 3:
                    printTree();
                    RemoveTree();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Wrong choice enter Again");
                    choice = -2;

            }
            if (choice == 4) {
                break;
            }

        }

    }

    public void RemoveTree() {

        int input;

        while (true) {
            try {
                System.out.print("Enter Tree number => ");
                input = sc.nextInt();
                tree.remove(input - 1);
                System.out.println("Remove Success");
                sortorderTree();
                break;
            } catch (Exception e) {
                // System.out.println(e);
                System.out.println("Remove failed   choose tree in scope");
                sc.nextLine();
            }

        }

    }

    public void AddTree() {

        int input;
        while (true) {
            try {

                System.out.print("Enter height => ");
                input = sc.nextInt();
                if (input < 0) {
                    throw new IllegalArgumentException("");
                }
                tree.add(new Tree("T" + String.valueOf(tree.size() + 1), input));
                System.out.println("Add Success");
                break;
            } catch (Exception e) {

                System.out.println(" input must be Postitive Integer Please Try again..");
                sc.nextLine();
            }

        }

    }

    public void printTree() {

        for (Tree t : tree) {
            System.out.printf("%2s. -> %2d foot\n", t.getname(), t.getheight());
        }

    }

}
