package com.ryan.tree;

/**
 * Created by MUFCRyan on 2017/11/2.
 * 练习 4.33
 */

public class D33_RemoveAllLeafNodes<AnyType extends Comparable<? super AnyType>> {

    public static void main(String[] args){
        D23_InsertAVLNoRecursion<Integer> insertAVLNoRecursion = new D23_InsertAVLNoRecursion<>();
        Node<Integer> root = new Node<>(null, null, null, 1);
        for (int i = 2; i <= 15; i++) {
            Integer element = i;
            root = insertAVLNoRecursion.insert(element, root);
        }

        D33_RemoveAllLeafNodes<Integer> removeAllLeafNodes = new D33_RemoveAllLeafNodes<>();
        removeAllLeafNodes.removeAllLeafNodes(root);
    }

    /**
     * 只会将最下面一层的层叶子节点删除，原因在于先判断当前节点是否是叶子节点以确定删除与否，之后才会检测其子节点，当递归执行到底开始返回时，程序只会执行判断叶节点代码以下的部分；
     * 这样就避免了删除掉原本不应该删除的上层的节点（子节点被删除后其本身也称为了叶节点，但是当前执行期间不应被删除）
     */
    private Node<AnyType> removeAllLeafNodes(Node<AnyType> tree){
        // 删除叶节点
        if (tree == null || (tree.left == null && tree.right == null))
            return null;
        tree.left = removeAllLeafNodes(tree.left);
        tree.right = removeAllLeafNodes(tree.right);
        return tree;
    }
}
