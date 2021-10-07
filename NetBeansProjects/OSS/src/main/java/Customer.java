
public class Customer {

    private String name, type;
    private int[] order;
    private int totalBill = 0, totalWeight = 0;

    Customer(String n, String t, int[] o) {
        name = n;
        type = t;
        order = o;
    }

    public void print() {
        System.out.printf("%s %s %d %d %d %d %d\n", name, type, order[0], order[1], order[2], order[3], order[4]);
    }
    

    public String getname() {
        return name;
    }

    public String gettype() {
        return type;
    }

    public int[] getorder() {
        return order;
    }

    public int gettotalBill() {
        return totalBill;
    }

    public int gettotalWeight() {
        return totalWeight;
    }

    public void updatebill(int price) {
        totalBill = totalBill + price;
    }

    public void updateweight(int weight) {
        totalWeight = totalWeight + weight;
    }
}
