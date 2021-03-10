import java.util.Scanner;

public class RBTreeTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RBTree<String, Object> rbt = new RBTree();
//
        while (true){
            System.out.println("请输入key: ");
            String key = sc.next();
            int key1 = Integer.parseInt(key);
            System.out.println();
            RBTree.RBNode node = new RBTree.RBNode();
            node.setKey(key1);
            rbt.insert(node);

            TreeOperation.show(rbt.getRoot());
        }
//        RBTree.RBNode node1 = new RBTree.RBNode();
//        RBTree.RBNode node2 = new RBTree.RBNode();
//        node1.setKey(9);
//        node2.setKey(10);
//        Comparable v1 = node1.getKey();
//        Comparable v2 = node2.getKey();
//        System.out.println(v1.compareTo(v2));

    }
}
