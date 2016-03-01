package russell.tests.algorithms;




public class Tree {

    static class Node {
        int data;
        Node left = null;
        Node right = null;
        
        public Node(int data) {
            this.data = data;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + data;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (data != other.data)
                return false;
            return true;
        }
    }
    
    Node root;
    
    
    public static void main(String[] args) {
        
        final Tree tree = new Tree();
        tree.root = new Node(0);
        tree.root.left = new Node(1);
        tree.root.right = new Node(2);
        
        tree.root.left.left = new Node(3);
        tree.root.left.right = new Node(4);
        
        tree.root.right.left = new Node(5);

        tree.root.right.left.left = new Node(6);
        tree.root.right.left.left.left = new Node(7);
        tree.root.right.left.left.left.left = new Node(8);
        
        System.out.println(getHeight(tree.root));
    }
    
    
    public static int getHeight(final Node node) {
        
        int leftHeight = 0;
        int rightHeight = 0;
        if (node.left != null) {
            leftHeight = getHeight(node.left);
        }
        if (node.right != null) {
            leftHeight = getHeight(node.right);
        }
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
