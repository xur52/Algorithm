public class RBTree <k extends Comparable<k>, v>{
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**树根的引用*/
    private RBNode root;

    public RBNode getRoot() {
        return root;
    }

    /**
     * 获取当前父节点
     * @param node
     */
    private RBNode parentof(RBNode node){
        if (node != null){
            return node.parent;
        }
        return null;
    }

    /**
     *  判断节点是否为红色
     * @param node
     */
    private boolean isRed(RBNode node){
        if (node != null){
            return node.color == RED;
        }
        return false;
    }

    /**
     *  判断节点是否为黑色
     * @param node
     */
    private boolean isBlack(RBNode node){
        if (node != null){
            return node.color == BLACK;
        }
        return false;
    }

    /**
     *  设定节点为红色
     * @param node
     */
    private void setRed(RBNode node){
        if (node != null) {
            node.color = RED;
        }
    }

    /**
     *  设定节点为黑色
     * @param node
     */
    private void setBlack(RBNode node){
        if (node != null) {
            node.color = BLACK;
        }
    }

    /**
     * 中序打印二叉树
     */
    public void inOrderPrint(){
        inOrderPrint(this.root);

    }

    private void inOrderPrint(RBNode root){
        if (root != null){
            inOrderPrint(root.left);
            System.out.println("key:" + root.key + ",value:" + root.value);
            inOrderPrint(root.right);
        }
    }

    /**
     * left-rotate
     * @param x
     * */
    private void leftRotate(RBNode x){
        RBNode y = x.right;
        //1. 将x的右子节点指向y的左子节点，更新y的左子节点的父节点为x
        x.right = y.left;
        if (y.left != null){
            y.left.parent = x;
        }
        // 2. 更新y的父节点（不为空的情况下）为x的父节点， 并将x的父节点的指定子节点指向y; 当父节点为空的时候，说明x为root，更新
        // y为root, 且root的parent为null
        if (x.parent != null){
            y.parent = x.parent;
            if (x == x.parent.left){
                x.parent.left = y;
            }else {
                x.parent.right = y;
            }
        }else{
            this.root = y;
            this.root.parent = null;

        }

        // 3. 将x的父节点更新为y，将y的左子节点更新为x
        x.parent = y;
        y.left = x;

    }

    /**
     * right rotate
     * @param y
     * */
    private void rightRotate(RBNode y){
        RBNode x = y.left;
        // 1. 将y的左子节点指向x的右子节点， 更新x的右子节点的父节点为y
        y.left = x.right;
        if (x.right != null){
            y.right.parent = y;
        }

        //2. 将x的父节点（不为空时）更新为y的父节点，并将y的父节点的指定子节点指向x；当父节点为空的时候，说明y为root，更新x为root。
        if (y.parent != null){
            x.parent = y.parent;
            if (y == y.parent.left){
                y.parent.left = x;
            } else{
                y.parent.right = x;
            }
        }else{
            this.root = x;
            this.root.parent = null;
        }
        //3. 将y的父节点更新为x， x的右子节点更新为y
        y.parent = x;
        x.right = y;
    }

    public void insert(k key, v value){
        RBNode node = new RBNode();
        node.setColor(RED);
        node.setKey(key);
        node.setValue(value);
        insert(node);

    }

    public void insert(RBNode node){

        //第一步，查找当前node的父节点
        RBNode parent = null;
        RBNode x = this.root;

        while (x != null){
            parent = x;
            Comparable v1 = node.getKey();
            Comparable v2 =  x.getKey();
            int cmp = v1.compareTo(v2);
            if (cmp > 0){
                x = x.right;

            }else if (cmp == 0){
                x.setValue(node.getValue());
                return;
            }else{
                x = x.left;

            }
        }
        node.setParent(parent);

        if (parent != null){
            int cmp = node.getKey().compareTo(parent.getKey());
            if (cmp > 0){
                parent.right = node;
            }else{
                parent.left = node;
            }
        }else{
            this.root = node;
        }

        //需要调用修复红黑树平衡的方法 insertFixup
        insertFixup(node);
    }

    private void insertFixup(RBNode node) {
        this.root.setColor(BLACK);

        RBNode parent = parentof(node);
        RBNode gparent = parentof(parent);

        //情景4：插入节点的父节点是红色
        if (parent != null && isRed(parent)){
            //如果父节点是红色，那么一定存在爷爷节点

            RBNode uncle = null;
            //4.1 插入节点的父节点是祖父节点的左子节点
            if (parent == gparent.left){
                uncle = gparent.right;

                //4.1.1 叔叔节点存在，并且叔叔和父亲都为红色
                if (uncle != null && isRed(uncle)){
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixup(gparent);
                    return;
                }
                //4.1.2 叔叔节点不存在，或者为黑色
                if (uncle == null || isBlack(uncle)){
                    if (node == parent.left){
                        setBlack(parent);
                        setRed(gparent);
                        rightRotate(gparent);
                        return;
                    }else{
                        leftRotate(parent);
                        insertFixup(parent);
                        return;
                    }


                }


            }else{
                uncle = gparent.left;
                //4.1.1 叔叔节点存在，并且叔叔和父亲都为红色
                if (uncle != null && isRed(uncle)){
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixup(gparent);
                    return;
                }
                if (uncle == null || isBlack(uncle)){
                    if (node == parent.right){
                        setBlack(parent);
                        setRed(gparent);
                        leftRotate(gparent);
                        return;
                    }else{
                        rightRotate(parent);
                        insertFixup(parent);
                        return;
                    }
                }

            }


        }



    }


    static class RBNode <k extends Comparable<k>, v>{
        private RBNode parent;
        private RBNode left;
        private RBNode right;
        private boolean color;
        private k key;
        private v value;

        public RBNode(){}

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, k key, v value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public k getKey() {
            return key;
        }

        public void setKey(k key) {
            this.key = key;
        }

        public v getValue() {
            return value;
        }

        public void setValue(v value) {
            this.value = value;
        }
    }
}
