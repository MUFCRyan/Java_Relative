package com.ryan.tree;

/**
 * Created by MUFCRyan on 2017/11/1.
 * 练习 4.23 写出向 AVL 树进行插入的非递归方法
 */

public class D23_InsertAVLNoRecursion<AnyType extends Comparable<? super AnyType>> {
    public static void main(String[] args){
        D23_InsertAVLNoRecursion<Integer> insertAVLNoRecursion = new D23_InsertAVLNoRecursion<>();
        Node<Integer> root = new Node<>(null, null, null, 1);
        for (int i = 2; i <= 15; i++) {
            Integer element = i;
            insertAVLNoRecursion.insert(element, root);
        }

        Node<Integer> tree = root;
    }

    private Node<AnyType> insert(AnyType element, Node<AnyType> root){
        if (root == null){
            root = new Node<>(null, null, null, element);
            return root;
        }

        Node<AnyType> tree = root;
        while (true){
            int compareResult = element.compareTo(tree.element);
            if (compareResult < 0){
                if (tree.left != null){
                    tree = tree.left;
                } else {
                    tree.left = new Node<>(tree, null, null, element);
                    Node<AnyType> currentTree = tree.left;
                    currentTree.setHeight(1);
                    while (currentTree != null){
                        // 遍历调整高度
                        currentTree.height = Math.max(getHeight(currentTree.left), getHeight(currentTree.right)) + 1; //
                        if (getHeight(currentTree.left) - getHeight(currentTree.right) == 2){
                            // 调整子树的平衡
                            int result = element.compareTo(currentTree.left.element);
                            if (result < 0){
                                rotateWithLeftChild(currentTree);
                            } else {
                                doubleWithLeftChild(currentTree);
                            }
                        }
                        if (currentTree.previous == null){
                            root = currentTree;
                        }
                        currentTree = currentTree.previous;
                    }
                    return tree.left;
                }
            } else if (compareResult > 0){
                if (tree.right != null){
                    tree = tree.right;
                } else {
                    tree.right = new Node<>(tree, null, null, element);
                    Node<AnyType> currentTree = tree.right;
                    currentTree.setHeight(1);
                    while (currentTree != null){
                        // 遍历调整高度
                        currentTree.height = Math.max(getHeight(currentTree.left), getHeight(currentTree.right)) + 1;
                        if (getHeight(currentTree.right) - getHeight(currentTree.left) == 2){
                            // 调整子树的平衡
                            int result = element.compareTo(currentTree.right.element);
                            if (result > 0){
                                rotateWithRightChild(currentTree);
                            } else {
                                doubleWithRightChild(currentTree);
                            }
                        }
                        if (currentTree.previous == null){
                            root = currentTree;
                        }
                        currentTree = currentTree.previous;
                    }
                    return tree.right;
                }
            } else // Duplicate
                return tree;
        }
    }

    private int getHeight(Node<AnyType> node){
        if (node == null)
            return 0;
        return node.height;
    }

    /** LL Rotation */
    private Node<AnyType> rotateWithLeftChild(Node<AnyType> k2){
        Node k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        // 高度计算时必须先计算子树的高度才能计算其父节点的高度，否则会因为子树高度未计算完毕导致父节点高度计算有误
        k2.height = Math.max(getHeight(k2.left), getHeight(k2.right)) + 1;
        k1.height = Math.max(getHeight(k1.left), k2.height) + 1;
        return k1;
    }

    /** RR Rotation */
    private Node<AnyType> rotateWithRightChild(Node<AnyType> k2){
        Node k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        // 高度计算时必须先计算子树的高度才能计算其父节点的高度，否则会因为子树高度未计算完毕导致父节点高度计算有误
        k2.height = Math.max(getHeight(k2.left), getHeight(k2.right)) + 1;
        k1.height = Math.max(k2.height, getHeight(k1.right)) + 1;
        return k1;
    }

    /** LR Rotation */
    private Node<AnyType> doubleWithLeftChild(Node<AnyType> k3){
        // 先将左子树与其下的左子树进行右单旋，然后再将旋转后的子树与 k3 进行左单旋
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /** RL Rotation */
    private Node<AnyType> doubleWithRightChild(Node<AnyType> k3){
        // 先将右子树与其下的右子树进行左单旋，然后再将旋转后的子树与 k3 进行右单旋
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }

    private static class Node<AnyType>{
        private Node<AnyType> previous;
        private Node<AnyType> left;
        private Node<AnyType> right;
        private AnyType element;
        private int height = 0;

        Node(Node<AnyType> previous, Node<AnyType> left, Node<AnyType> right, AnyType element) {
            this.previous = previous;
            this.left = left;
            this.right = right;
            this.element = element;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
