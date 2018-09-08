public class BinaryTree {

    Node root;

    void addNode(String key, String name, int gender) {
        Node newNode = new Node(key, name, gender);

        if (root == null) {
            root = newNode;
        } else {
            Node focusNode = root;
            while (true) {
                Node parentNode = focusNode;
                if (key.compareTo(parentNode.key) < 0) { // key has a smaller value than parentNode i.e. key=A, parentNode=B
                    //GO LEFT
                    focusNode = focusNode.leftChild;
                    if (focusNode == null) {
                        parentNode.leftChild = newNode;
                        return;
                    }
                } else {
                    //GO RIGHT
                    focusNode = focusNode.rightChild;
                    if (focusNode == null) {
                        parentNode.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public void inOrderTraversal(Node focusNode){
        if(focusNode != null){
            inOrderTraversal(focusNode.leftChild);
            //print value
            inOrderTraversal(focusNode.rightChild);
        }

    }
}


class Node {
    String key;
    String name;
    int gender;

    Node leftChild;
    Node rightChild;

    public Node(String key, String name, int gender) {
        this.key = key;
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return this.name;
    }
}

