
public class Rainfall {

    public static double average_rainfall(int[] rainfall) {
        double total =0;
        double number = 0;
        for(int i = 0; i < rainfall.length; i++) {
            if (rainfall[i] >= 0) {
                total += rainfall[i];
                number += 1;
            }
            if (rainfall[i] == -999) {
                break;
            }
        }
        if(number == 0){
                throw new IllegalArgumentException();
        }

        return total/number;
    }

}

