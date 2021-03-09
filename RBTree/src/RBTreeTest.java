import java.util.Scanner;

public class RBTreeTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RBTree<String, Object> rbt = new RBTree();

        while (true){
            System.out.println("请输入key: ");
            String key = sc.next();
            System.out.println();
            rbt.insert(key, null);

            TreeOperation.show(rbt.getRoot());
        }
    }
}
