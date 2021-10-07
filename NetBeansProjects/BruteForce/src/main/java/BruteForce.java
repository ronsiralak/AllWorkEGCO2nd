
public class BruteForce {

    public static void main(String[] args) {
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
                    for (int k =  i+1; k <= j-1 ; k++) {
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
    }

}
