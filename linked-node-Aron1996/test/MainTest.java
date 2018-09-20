import static org.junit.Assert.*;

public class MainTest {
    @org.junit.Test
    public void array_to_list() throws Exception {
        int[] A = {1,2,3};
        Node n = Main.array_to_list(A);
        assert n.data == 1;
        assert n.next.data == 2;
        assert n.next.next.data == 3;
    }

    @org.junit.Test
    public void nth_node() throws Exception {
        int[] A = {1,2,3};
        Node n = Main.array_to_list(A);
        for (int i = 0; i != 3; ++i)
            assert Main.nth_node(n, i).data == i+1;
    }

    @org.junit.Test
    public void remove_next() throws Exception {
        int[] A = {1,2,3};
        Node n = Main.array_to_list(A);
        Main.remove_next(n);
        assert n.data == 1;
        assert n.next.data == 3;
        assert n.next.next == null;
    }

    @org.junit.Test
    public void insert_after() throws Exception {
        int[] A = {1,2,4};
        Node n = Main.array_to_list(A);
        Main.insert_after(n.next, 3);
        assert n.next.next.data == 3;
        assert n.next.next.next.data == 4;

        int[] B = {4, 5, 7};
        Node n1 = Main.array_to_list(B);
        Main.insert_after(n1.next, 6);
        assert n1.next.next.data == 6;
        assert n1.next.data == 5;

        int[] C = {10, 11, 12, 14};
        Node n2 = Main.array_to_list(C);
        Main.insert_after(n2.next.next, 13);
        assert n2.next.next.next.data == 13;
        assert n2.next.next.next.next.data == 14;
    }



}