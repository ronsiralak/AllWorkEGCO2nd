
public class Postage {

    private String type, maxWeight;
    private int  rate;
    private Double minWeight,Maxw;

    Postage(String t, double minW, String maxW, int R) {
        type = t;
        minWeight = minW;
        maxWeight = maxW;
        rate = R;
        if ("inf".equals(maxW)) {
            Maxw = Double.POSITIVE_INFINITY;
        } else {
            Maxw = Double.parseDouble(maxW);
        }

    }

    public void print() {
        System.out.printf("%s %.1f %.1f %d\n", type, minWeight, Maxw, rate);
    }

    public String gettype() {
        return type;
    }

    public double getminweight() {
        return minWeight;
    }
    
    public double getmaxweight() {
        return Maxw;
    }

    public int getrate() {
        return rate;
    }
}
