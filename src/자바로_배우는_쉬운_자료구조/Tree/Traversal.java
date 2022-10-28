package 자바로_배우는_쉬운_자료구조.Tree;

class TreeNode{
    Object data;
    TreeNode left;
    TreeNode right;
}

class LinkedTree{
    private TreeNode root;

    public TreeNode makeBT(TreeNode node01, Object data, TreeNode node02){
        TreeNode root = new TreeNode();
        root.data = data;
        root.left = node01;
        root.right = node02;
        return root;
    }

    public void preorder(){
        preorder(this.root);
        System.out.println();
    }

    public void preorder(TreeNode node){
        if (node == null) return;
        System.out.printf("%c",node.data);
        preorder(node.left);
        preorder(node.right);
    }

    public void inorder(){
        inorder(this.root);
        System.out.println();
    }

    public void inorder(TreeNode node){
        if (node == null) return;
        inorder(node.left);
        System.out.printf("%c",node.data);
        inorder(node.right);
    }

    public void postorder(){
        postorder(this.root);
        System.out.println();
    }

    public void postorder(TreeNode node){
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.printf("%c",node.data);
    }

    public void setRoot(TreeNode root){
        this.root = root;
    }

}

public class Traversal {
    public static void main(String[] args) {
        LinkedTree tree = new LinkedTree();

        TreeNode n7 = tree.makeBT(null,'D',null);
        TreeNode n6 = tree.makeBT(null,'C',null);
        TreeNode n5 = tree.makeBT(null,'B',null);
        TreeNode n4 = tree.makeBT(null,'A',null);
        TreeNode n3 = tree.makeBT(n6,'/',n7);
        TreeNode n2 = tree.makeBT(n4,'*',n5);
        TreeNode n1 = tree.makeBT(n2,'-',n3);

        tree.setRoot(n1);

        tree.preorder();
        tree.inorder();
        tree.postorder();
    }
}
