import junit.framework.TestCase;

public class MainTest extends TestCase {
    public void testAverage_rainfall() throws Exception {
        {
            /*{
                int A[] = {-999, 3};
                double average = Rainfall.average_rainfall(A);
                assertTrue(false);
            }*/
            {
                int A[] = {1, 2, 3};
                double average = Rainfall.average_rainfall(A);
                assertEquals(2.0, average);
            }
            int B[] = {1,2,3,4,5,-999,3,4,5};
            double average2 = Rainfall.average_rainfall(B);
            assertEquals(3.0, average2);

            try{
                int C[] = {-1,-2,-3};
                double average3 = Rainfall.average_rainfall(C);
                assertTrue(false);
            }catch(IllegalArgumentException e){
                System.err.println("Caught Exception" + e.getMessage());
            }
        }

    }

}