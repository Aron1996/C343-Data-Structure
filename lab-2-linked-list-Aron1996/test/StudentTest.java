import static org.junit.Assert.*;

public class StudentTest {
    @org.junit.Test
    public void begin() throws Exception {
        LinkedList<Integer> L = new LinkedList<Integer>();
        L.begin().equals(L.end());
    }

    @org.junit.Test
    public void insert_front() throws Exception {
        {
            LinkedList<Integer> L = new LinkedList<Integer>();
            L.insert_front(3);
            assertEquals( (int)L.begin().get(), 3);
        }
        {
            // Test a sequence of insert_front's
            LinkedList<Integer> L = new LinkedList<Integer>();
            L.insert_front(3); L.insert_front(2); L.insert_front(1);
            Integer A[] = {1,2,3};
            assertTrue(SeqAlgo.equals(L, new ArraySequence<Integer>(A)));
        }
        {
            LinkedList<Integer> L1 = new LinkedList<Integer>();
            L1.insert_front(4); L1.insert_front(5); L1.insert_front(6);
            Integer A[] = {6,5,4};
            assertTrue(SeqAlgo.equals(L1, new ArraySequence<Integer>(A)));
        }
    }

    @org.junit.Test
    public void insert_after() throws Exception {
        LinkedList<Integer> L = new LinkedList<Integer>();
        L.insert_front(1);
        LinkedList<Integer>.ListIter i = L.begin();

        // Test insert_after on the first position
        L.insert_after(i, 2);

        // Test insert_after on the last position
        i = L.begin(); i.advance();
        L.insert_after(i, 4);
        Integer B[] = {1,2,4};
        assertTrue(SeqAlgo.equals(L, new ArraySequence<Integer>(B)));

        // Test insert_after in the middle
        i = L.begin(); i.advance();
        L.insert_after(i, 3);
        Integer C[] = {1,2,3,4};
        assertTrue(SeqAlgo.equals(L, new ArraySequence<Integer>(C)));

        i = L.begin(); i.advance();
        L.insert_after(i, 3);
        Integer D[] = {1,2,3,3,4};
        assertTrue(SeqAlgo.equals(L, new ArraySequence<Integer>(D)));
    }

}