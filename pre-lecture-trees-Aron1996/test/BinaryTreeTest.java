import junit.framework.TestCase;

import java.util.ArrayList;

public class BinaryTreeTest extends TestCase {
    BinaryTree<Integer> T;
    BinaryTree<Integer> T2;
    BinaryTree<Integer> T3;

    public BinaryTreeTest() { }

    @Override
    public void setUp() throws Exception {
        BinaryNode<Integer> n2 = new BinaryNode<>(2,null,null);
        BinaryNode<Integer> n5 = new BinaryNode<>(5,null,null);
        BinaryNode<Integer> n25 = new BinaryNode<>(5,n2,n5);
        BinaryNode<Integer> n8 = new BinaryNode<>(8, null, null);
        BinaryNode<Integer> n7 = new BinaryNode<>(7, null, n8);
        BinaryNode<Integer> r = new BinaryNode<>(6, n25, n7);
        T = new BinaryTree<>(r);

        BinaryNode<Integer> n26 = new BinaryNode<>(26, null, null);
        BinaryNode<Integer> n10 = new BinaryNode<>(10, null, n26);
        BinaryNode<Integer> r1 = new BinaryNode<>(9,n8,n10);
        T2 = new BinaryTree<>(r1);

        BinaryNode<Integer> n3 = new BinaryNode<>(3, null, null);
        BinaryNode<Integer> r2 = new BinaryNode<>(5,n3,n7);
        T3 = new BinaryTree<>(r2);
    }

    @org.junit.Test
    public void testPreorder() throws Exception {
        {
            ArrayList<Integer> B = new ArrayList<>();
            T.preorder((Integer k) -> { B.add(k); });
            int expected[] = { 6, 5, 2, 5, 7, 8 };
            for (int i = 0; i != expected.length; ++i)
                assertEquals((Integer)expected[i], B.get(i));
        }
        {
            ArrayList<Integer> B = new ArrayList<>();
            T2.preorder((Integer k) -> { B.add(k); });
            int expected[] = { 9, 8, 10 ,26};
            for (int i = 0; i != expected.length; ++i)
                assertEquals((Integer)expected[i], B.get(i));
        }
        {
            ArrayList<Integer> B = new ArrayList<>();
            T3.preorder((Integer k) -> { B.add(k); });
            int expected[] = { 5,3,7,8 };
            for (int i = 0; i != expected.length; ++i)
                assertEquals((Integer)expected[i], B.get(i));
        }
    }

    @org.junit.Test
    public void testInorder() throws Exception {
        {
            ArrayList<Integer> B = new ArrayList<>();
            T.inorder((Integer k) -> { B.add(k); });
            int expected[] = { 2, 5, 5, 6, 7, 8 };
            for (int i = 0; i != expected.length; ++i)
                assertEquals((Integer)expected[i], B.get(i));
        }
        {
            ArrayList<Integer> B = new ArrayList<>();
            T2.inorder((Integer k) -> { B.add(k); });
            int expected[] = { 8,9,10,26 };
            for (int i = 0; i != expected.length; ++i)
                assertEquals((Integer)expected[i], B.get(i));
        }
        {
            ArrayList<Integer> B = new ArrayList<>();
            T3.inorder((Integer k) -> { B.add(k); });
            int expected[] = { 3,5,7,8 };
            for (int i = 0; i != expected.length; ++i)
                assertEquals((Integer)expected[i], B.get(i));
        }
    }

    @org.junit.Test
    public void testPostorder() throws Exception {
        {
            ArrayList<Integer> B = new ArrayList<>();
            T.postorder((Integer k) -> { B.add(k); });
            int expected[] = { 2, 5, 5, 8, 7, 6 };
            for (int i = 0; i != expected.length; ++i)
                assertEquals((Integer)expected[i], B.get(i));
        }
        {
            ArrayList<Integer> B = new ArrayList<>();
            T2.postorder((Integer k) -> { B.add(k); });
            int expected[] = { 8,26,10,9 };
            for (int i = 0; i != expected.length; ++i)
                assertEquals((Integer)expected[i], B.get(i));
        }
        {
            ArrayList<Integer> B = new ArrayList<>();
            T3.postorder((Integer k) -> { B.add(k); });
            int expected[] = { 3,8,7,5};
            for (int i = 0; i != expected.length; ++i)
                assertEquals((Integer)expected[i], B.get(i));
        }
    }
}