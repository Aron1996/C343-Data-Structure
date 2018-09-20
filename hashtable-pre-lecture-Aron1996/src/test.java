import java.util.Scanner;
public class test {

    public static int method1(int i){
        int output = 0;
        for(int j = 0; j < 5; j++){
            if((i / (10 * Math.exp(j)) == 0 )){
                output = j;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int a = in.nextInt();
            System.out.println(method1(a));
        }
    }

}
