public class Main {

    static void rotate(int[] A) {
        int[] temp = A.clone();
        int length = A.length;
        for (int i = 0;i < length;i++){
            if(i == length-1){
                A[0] = temp[i];
            }else{
                A[i+1] = temp[i];
            }
        }
    }

}


