import junit.framework.TestCase;
import java.util.Arrays;

public class MainTest extends TestCase {
    public void testRotate() throws Exception {
        {
            int[] A = {7,4,12,53,32};
            int[] A_shifted = {32,7,4,12,53};
            Main.rotate(A);
            assertTrue(Arrays.equals(A, A_shifted));

            int[] B = {1,2,3,4};
            int[] B_shifted = {4,1,2,3};
            Main.rotate(B);
            assertTrue(Arrays.equals(B, B_shifted));

            int[] C = {2,3,4};
            int[] C_shifted = {4,2,3};
            Main.rotate(C);
            assertTrue(Arrays.equals(C, C_shifted));

            int[] D = {10,11,12,13,14,15};
            int[] D_shifted = {15,10,11,12,13,14};
            Main.rotate(D);
            assertTrue(Arrays.equals(D, D_shifted));
        }
    }

}