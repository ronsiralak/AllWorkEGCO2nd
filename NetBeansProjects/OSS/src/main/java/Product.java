
public class Product {

    private String name;
    private int price, weight, totalSalesInCash = 0, totalSalesInUnits = 0;

    Product(String n, int p, int w) {
        name = n;
        price = p;
        weight = w;
    }

    public void print() {
        System.out.printf("%s %d %d\n", name, price, weight);
    }

    public String getname() {
        return name;
    }

    public int getprice() {
        return price;
    }

    public int getweight() {
        return weight;
    }

    public int gettotalsalecash() {
        return totalSalesInCash;
    }

    public int gettotalsaleunit() {
        return totalSalesInUnits;
    }
    public void updatesell(int number){
    totalSalesInCash  = totalSalesInCash + (price*number);
    totalSalesInUnits = totalSalesInUnits+number;
    }
}
