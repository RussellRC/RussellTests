package org.russell.algorithms.ds;


public class TrinaryTree {
    
    public static void main(String[] args) {
        // Node root = null;
        // root = insertRecursive(root, 5);
        // insertRecursive(root, 4);
        // insertRecursive(root, 9);
        // insertRecursive(root, 5);
        // insertRecursive(root, 7);
        // insertRecursive(root, 2);
        // insertRecursive(root, 2);
        // insertRecursive(root, 10);
        // System.out.println("====== IN ORDER =====");
        // inOrder(root);
        // delete(root, 2);

        TrinaryNode root2 = null;
        root2 = insertRecursive(root2, 25);
        insertRecursive(root2, 50);
        // derecha
        insertRecursive(root2, 35);
        insertRecursive(root2, 30);
        insertRecursive(root2, 45);
        insertRecursive(root2, 100);
        insertRecursive(root2, 75);
        insertRecursive(root2, 150);
        insertRecursive(root2, 65);
        insertRecursive(root2, 85);

        // System.out.println();
        // deleteNode_Russell(root2, 100);
        // inOrder(root2);
        //
        // System.out.println();
        // deleteNode_Russell(root2, 150);
        // inOrder(root2);

        System.out.println();
        deleteNode_Russell(root2, 50);
        inOrder(root2);
        
        //izq
        insertRecursive(root2, 10);
        insertRecursive(root2, 15);
        insertRecursive(root2, 12);
        insertRecursive(root2, 20);
        insertRecursive(root2, 8);
        insertRecursive(root2, 7);
        insertRecursive(root2, 9);
        System.out.println();
        inOrder(root2);
        
        System.out.println("\n\nDelete 10");
        deleteNode_Russell(root2, 10);
        inOrder(root2);
        
        System.out.println("\n\nDelete 20");
        deleteNode_Russell(root2, 20);
        inOrder(root2);
        
        System.out.println("\n\nDelete 7");
        deleteNode_Russell(root2, 7);
        inOrder(root2);
    }

    static TrinaryNode insert(TrinaryNode root, int value) {
        TrinaryNode newNode = new TrinaryNode(value);
        if (root == null) {
            return newNode;
        }
        TrinaryNode node = root;
        while (node != null) {
            if (value > node.data) {
                if (node.right == null) {
                    node.right = newNode;
                    break;
                }
                node = node.right;
            } else if (value < node.data) {
                if (node.left == null) {
                    node.left = newNode;
                    break;
                }
                node = node.left;
            } else if (value == node.data) {
                if (node.mid == null) {
                    node.mid = newNode;
                    break;
                }
                node = node.mid;
            }
        }

        return root;
    }

    static TrinaryNode insertRecursive(TrinaryNode root, int value) {
        TrinaryNode newNode = new TrinaryNode(value);
        if (root == null) {
            return newNode;
        }
        TrinaryNode node = root;
        while (node != null) {
            if (value > node.data) {
                node.right = insertRecursive(node.right, value);
                break;
            } else if (value < node.data) {
                node.left = insertRecursive(node.left, value);
                break;
            } else if (value == node.data) {
                node.mid = insertRecursive(node.mid, value);
                break;
            }
        }

        return root;
    }

    static TrinaryNode delete(TrinaryNode root, int value) {
        if(root == null){
            System.out.println("Can not perform deletion, Tree is empty.");
            return null;
        }
        TrinaryNode node = root;
        while (node.right != null || node.left != null) {
            if (node.right != null && value > node.right.data) {
                node = node.right;
            } else if (node.left != null && value < node.left.data) {
                node = node.left;
            } else if (value == node.left.data) {
                if(node.left.left!=null && node.left.right == null && node.left.mid == null){
                    node.left = null;
                }
                if (node.left.mid != null) {
                    node.left.mid.right = node.left.right;
                    node.left.mid.left = node.left.left;
                    node.left = node.left.mid;
                    break;
                }else{
                    TrinaryNode temp = getMostToTheRightNode(node.left);
                    temp.left = node.left.left;
                    temp.right = node.left.right;
                    node.left = temp;
                }
            } else if (value == node.right.data) {
                if (node.right.left != null && node.right.right == null && node.right.mid == null) {
                    node.left = null;
                }
                if (node.right.mid != null) {
                    node.right = node.right.mid;
                    break;
                }else{
                    
                }
            }
        }
        return root;
    }
    
    static TrinaryNode deleteNode_Russell(TrinaryNode root, int value) {
        if (root == null) {
            throw new RuntimeException("wtf, dude!!!");
        }
        
        // find node to delete
        TrinaryNode parent = null;
        TrinaryNode node = root;
        while (node != null && node.data != value) {
            parent = node;
            if (node.data < value) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        
        if (node == null) {
            // not found
            throw new RuntimeException("404, dude!");
        }
        
        TrinaryNode promoted = null;
        
        // Case1: mid
        if (node.mid != null) {
            promoted = node.mid;
            return deleteNode(node, parent, promoted);
        }
        
        TrinaryNode promotedParent = null;
        
        // Case2: left-most of the right
        if (node.right != null) {
            promotedParent = node;
            promoted = node.right;
            while (promoted.left != null) {
                promotedParent = promoted;
                promoted = promoted.left;
            }
            if (promotedParent != node) {
                // node.right is the promoted leaf
                promotedParent.left = null;
            }
            return deleteNode(node, parent, promoted);
        } 
        
        // Case 3: right-most of the left
        else if (node.left != null) {
            promotedParent = node;
            promoted = node.left;
            while (promoted.right != null) {
                promotedParent = promoted;
                promoted = promoted.right;
            }
            if (promotedParent != node) {
                // node.left is the promoted leaf
                promotedParent.right = null;
            }
            return deleteNode(node, parent, promoted);
        }
        
        // Case 4: leaf
        return deleteNode(node, parent, null);
    }
    
    /**
     * 
     * @param node - Node to delete
     * @param parent - Parent of the node to delete
     * @param promoted - Node that will replace the deleted node
     * @return
     */
    static TrinaryNode deleteNode(TrinaryNode node, TrinaryNode parent, TrinaryNode promoted) {
        // atach parent to promoted
        if (node == parent.left) {
            parent.left = promoted;
        } else {
            parent.right = promoted;
        }
        
        // attach children to promoted
        if (node.left != promoted) {
            promoted.left = node.left;
        }
        if (node.right != promoted) {
            promoted.right = node.right;
        }
        return promoted;
    }

    static TrinaryNode getMostToTheRightNode(TrinaryNode node) {
        TrinaryNode temp = node;
        while (node.right.right != null) {
            temp = temp.right;
        }
        TrinaryNode result = temp.right;

        if (temp.right.left != null) {
            temp.right = temp.right.left;
        }
        return result;
    }

    static void inOrder(TrinaryNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(" " + root.data + " | ");
        inOrder(root.mid);
        inOrder(root.right);
    }
}
