
import java.io.*;
import java.util.*;

class MyRuntimeEception extends RuntimeException {

    public MyRuntimeEception(String messgae) {
        super(messgae);
    }

}


public class OnlineShop {

    public static void main(String[] args) {
        ArrayList<Product> ProductDATA = new ArrayList<Product>();
        ArrayList<Postage> PostageDATA = new ArrayList<Postage>();
        ArrayList<Customer> CustomerDATA = new ArrayList<Customer>();
        CreateProduct(ProductDATA);
        CreatePostage(PostageDATA);
        CreateCustomer(CustomerDATA, ProductDATA);
        // printCheck(ProductDATA, PostageDATA, CustomerDATA);
        Object[][] data_person = new Object[CustomerDATA.size()][5];
        Object[][] data_item = new Object[ProductDATA.size()][3];

        for (int i = 0; i < CustomerDATA.size(); i++) {
            data_person[i][0] = CustomerDATA.get(i).getname();
            data_person[i][1] = calculateprice(ProductDATA, CustomerDATA.get(i));
            data_person[i][2] = calculateweight(ProductDATA, CustomerDATA.get(i));
            data_person[i][3] = calculatepostage(PostageDATA, CustomerDATA.get(i).gettype(), CustomerDATA.get(i).gettotalWeight());
            data_person[i][4] = (int) data_person[i][1] + (int) data_person[i][3];
        }
        dataITEM(ProductDATA, CustomerDATA);
        for (int i = 0; i < ProductDATA.size(); i++) {
            data_item[i][0] = ProductDATA.get(i).getname();
            data_item[i][1] = ProductDATA.get(i).gettotalsalecash();
            data_item[i][2] = ProductDATA.get(i).gettotalsaleunit();
        }

        /* for (int i = 0; i < CustomerDATA.size(); i++) {
            System.out.println();
            System.out.println();
            System.out.println("NEW PERSON");
            System.out.print(data_person[i][0] + " ");
            System.out.print(data_person[i][1] + " ");
            System.out.print(data_person[i][2] + " ");
            System.out.print(data_person[i][3] + " ");
            System.out.println(data_person[i][4] + " ");
            System.out.print(data_item[i][0] + " ");
            System.out.print(data_item[i][1] + " ");
            System.out.println(data_item[i][2] + " ");
            System.out.println("END---------------");
        }*/
        System.out.println();
        System.out.println("----- Process order -----");
        for (int i = 0; i < CustomerDATA.size(); i++) {
            int[] o = CustomerDATA.get(i).getorder();
            System.out.print(CustomerDATA.get(i).getname() + "  >>  ");
            for (int j = 0; j < ProductDATA.size(); j++) {
                System.out.printf("%s (%2d)   ", ProductDATA.get(j).getname(), o[j]);
            }
            System.out.println();
            System.out.print("           ");
            System.out.printf("product price = %6d   ", data_person[i][1]);
            System.out.printf("total weight  = %6d\n", data_person[i][2]);
            System.out.print("           ");
            System.out.printf("postage (%s)   = %6d   ", CustomerDATA.get(i).gettype(), data_person[i][3]);
            System.out.printf("total bill    = %6d\n", data_person[i][4]);
        }

        String[] nameC = new String[CustomerDATA.size()];
        int[] moneyC = new int[CustomerDATA.size()];
        for (int i = 0; i < CustomerDATA.size(); i++) {
            nameC[i] = data_person[i][0].toString();
            moneyC[i] = (int) data_person[i][4];
        }

        for (int i = 0; i < CustomerDATA.size() - 1; i++) {
            for (int j = 0; j < CustomerDATA.size() - i - 1; j++) {
                if (moneyC[j] < moneyC[j + 1]) {
                    int temp = moneyC[j];
                    moneyC[j] = moneyC[j + 1];
                    moneyC[j + 1] = temp;

                    String temps = nameC[j];
                    nameC[j] = nameC[j + 1];
                    nameC[j + 1] = temps;
                }
            }
        }
        System.out.println();
        System.out.println("----- Sort customers by total bill -----");
        for (int i = 0; i < CustomerDATA.size(); i++) {
            System.out.printf("%s   bill = %6d\n", nameC[i], moneyC[i]);
        }

        String[] nameP = new String[ProductDATA.size()];
        int[] moneyP = new int[ProductDATA.size()];
        int[] unitP = new int[ProductDATA.size()];
        for (int i = 0; i < ProductDATA.size(); i++) {
            nameP[i] = data_item[i][0].toString();
            moneyP[i] = (int) data_item[i][1];
            unitP[i] = (int) data_item[i][2];
        }
        for (int i = 0; i < ProductDATA.size() - 1; i++) {
            for (int j = 0; j < ProductDATA.size() - i - 1; j++) {
                if (moneyP[j] < moneyP[j + 1]) {
                    int temp = moneyP[j];
                    moneyP[j] = moneyP[j + 1];
                    moneyP[j + 1] = temp;

                    String temps = nameP[j];
                    nameP[j] = nameP[j + 1];
                    nameP[j + 1] = temps;

                    temp = unitP[j];
                    unitP[j] = unitP[j + 1];
                    unitP[j + 1] = temp;
                }
            }
        }
        System.out.println();
        System.out.println("----- Sort products by total sales in cash -----");
        for (int i = 0; i < ProductDATA.size(); i++) {
            System.out.printf("%-20stotal sales = %6d B,%5d units\n", nameP[i], moneyP[i], unitP[i]);
        }

    }

    public static void printCheck(ArrayList<Product> ProductDATA,
            ArrayList<Postage> PostageDATA,
            ArrayList<Customer> CustomerDATA) {
        for (int i = 0; i < ProductDATA.size(); i++) {
            ProductDATA.get(i).print();

        }
        for (int i = 0; i < PostageDATA.size(); i++) {
            PostageDATA.get(i).print();

        }
        for (int i = 0; i < CustomerDATA.size(); i++) {
            CustomerDATA.get(i).print();

        }
    }

    public static void CreateProduct(ArrayList<Product> pd) {
        String Filename = "";
        String line = "";
        Scanner file = new Scanner(System.in);
        boolean openfile = false;
        System.out.println("Enter file name for product");
        Filename = file.next();
        System.out.println("");
        while (!openfile) {
            try ( Scanner input = new Scanner(new File(Filename));) {

                if (!Filename.equals("products.txt")) {

                    throw new MyRuntimeEception("error type file");
                }
                openfile = true;
                try {
                    while (input.hasNext()) {
                        line = input.nextLine();
                        String[] buf = line.split(",");
                        int price = Integer.parseInt(buf[1].trim());
                        int weight = Integer.parseInt(buf[2].trim());

                        Product p = new Product(buf[0].trim(), price, weight);
                        pd.add(p);

                    }
                } catch (Exception e) {

                    System.out.println("input line >>" + line);
                    System.out.println(e);

                }
            } catch (FileNotFoundException f) {

                System.out.println("Error file product not found");
                System.out.println("New product filename ");
                Filename = file.next();
                System.out.println("");

            } catch (MyRuntimeEception me) {
                System.out.println(me);
                System.out.println("New product filename ");
                Filename = file.next();
                System.out.println("");
            }

        }
    }

    public static void CreatePostage(ArrayList<Postage> pt) {
        String Filename = "postagse.txt";
        String line = "";
        Scanner file = new Scanner(System.in);
        boolean openfile = false;
        System.out.println("Enter file name for Postage");
        Filename = file.next();
        System.out.println("");
        while (!openfile) {
            try ( Scanner input = new Scanner(new File(Filename));) {

                if (!Filename.equals("postages.txt")) {

                    throw new MyRuntimeEception("error type file");
                }
                openfile = true;
                try {

                    while (input.hasNext()) {
                        line = input.nextLine();
                        String[] buf = line.split(",");
                        int minW = Integer.parseInt(buf[1].trim());
                        int Rate = Integer.parseInt(buf[3].trim());
                        Postage p = new Postage(buf[0].trim(), minW, buf[2].trim(), Rate);
                        pt.add(p);

                    }
                } catch (Exception e) {
                    System.out.println("input line >>" + line);
                    System.out.println(e);
                }

            } catch (FileNotFoundException f) {

                System.out.println("Error file postages not found");
                System.out.println("New postages filename = ");
                Filename = file.next();
                System.out.println("");

            } catch (MyRuntimeEception me) {
                System.out.println(me);
                System.out.println("New postages filename ");

                Filename = file.next();
                System.out.println("");
            }
        }
    }

    public static void CreateCustomer(ArrayList<Customer> ctm, ArrayList<Product> PD) {

        String Filename = "customers.txt";
        String line = "";
        Scanner file = new Scanner(System.in);
        boolean openfile = false;
        System.out.println("Enter file name for Customer");
        Filename = file.next();
        System.out.println("");
        while (!openfile) {
            try ( Scanner input = new Scanner(new File(Filename));) {

                if (!Filename.equals("customers.txt")) {

                    throw new MyRuntimeEception("error type file");
                }
                openfile = true;

                while (input.hasNext()) {
                    try {
                        line = input.nextLine();
                        String[] buf = line.split(",");
                        boolean checknegative = false;
                        int[] order = new int[PD.size()];
                        for (int i = 0; i < PD.size(); i++) {
                            order[i] = Integer.parseInt(buf[i + 2].trim());
                            if (order[i] < 0) {
                                checknegative = true;
                            }
                        }
                        if (checknegative == false) {
                            if (buf[1].trim().equals("E") || buf[1].trim().equals("R")) {
                                Customer c = new Customer(buf[0].trim(), buf[1].trim(), order);
                                ctm.add(c);
                            } else {
                                System.out.println("ERROR : Postage type error -> " + line);
                            }
                        } else {
                            System.out.println("ERROR : Order negative -> " + line);
                        }

                    } catch (Exception e) {
                        System.out.println("input line >>" + line);
                        System.out.println(e);
                    }

                }

            } catch (FileNotFoundException e) {

                System.out.println("Error file customer not found");
                System.out.println("New customer filename = ");
                Filename = file.next();
                System.out.println("");

            } catch (MyRuntimeEception me) {
                System.out.println(me);
                System.out.println("New customer filename ");
                Filename = file.next();
                System.out.println("");
            }
        }
    }

    public static int calculateprice(ArrayList<Product> ProductDATA, Customer CustomerDATA) {
        int sum = 0, price, number, tmp;
        int[] order = CustomerDATA.getorder();
        for (int i = 0; i < ProductDATA.size(); i++) {
            price = ProductDATA.get(i).getprice();
            number = order[i];
            tmp = price * number;
            sum = sum + tmp;
        }
        //System.out.println(sum);
        CustomerDATA.updatebill(sum);
        return sum;
    }

    public static int calculateweight(ArrayList<Product> ProductDATA, Customer CustomerDATA) {
        int sum = 0, weight, number, tmp;
        int[] order = CustomerDATA.getorder();
        for (int i = 0; i < ProductDATA.size(); i++) {
            weight = ProductDATA.get(i).getweight();
            number = order[i];
            tmp = weight * number;
            sum = sum + tmp;
        }
        //System.out.println(sum);
        CustomerDATA.updateweight(sum);
        return sum;
    }

    public static int calculatepostage(ArrayList<Postage> PostageDATA, String type, int weight) {
        int cost = 0;
        double w = weight;
        String T;
        double min;
        double max;
        int R;
        for (int i = 0; i < PostageDATA.size(); i++) {

            T = PostageDATA.get(i).gettype();
            min = PostageDATA.get(i).getminweight();
            max = PostageDATA.get(i).getmaxweight();
            R = PostageDATA.get(i).getrate();

            if (T.equals(type) && w > min && w <= max) {
                cost = R;
                /* System.out.print(i);
                System.out.print("  ");
                System.out.print(weight);
                System.out.print("  ");
                System.out.print(T);
                System.out.print("  ");
                System.out.print(min);
                System.out.print("  ");
                System.out.print(max);
                System.out.print("  ");*/
            }
        }
        //System.out.println(cost);
        return cost;
    }

    public static void dataITEM(ArrayList<Product> ProductDATA, ArrayList<Customer> CustomerDATA) {
        int[] order;
        Object[][] data_item = new Object[5][3];
        int number;
        for (int i = 0; i < CustomerDATA.size(); i++) {
            //System.out.println(CustomerDATA.size());
            order = CustomerDATA.get(i).getorder();
            for (int j = 0; j < ProductDATA.size(); j++) {
                ProductDATA.get(j).updatesell(order[j]);
                /*System.out.print(order[j]);
                System.out.print("  ");*/
            }
            //System.out.println("end j loop");
        }

    }

}
