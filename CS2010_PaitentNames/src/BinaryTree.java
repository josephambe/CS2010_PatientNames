public class BinaryTree {

    Node root;
    Node parent;
    Boolean isLeftNode = true;

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

    public Node findPatient(String name){

        Node focusNode = root;
        Node parentNode = root;

        while(!focusNode.getName().equals(name)){
            parentNode = focusNode;

            if(name.compareTo(focusNode.getName()) < 0){
                //GO LEFT
                isLeftNode = true;
                focusNode = focusNode.leftChild;
            } else {
                isLeftNode = false;
                focusNode = focusNode.rightChild;
            }

            if(focusNode == null){
                return null;
            }
        }

        this.parent = parentNode;
        return focusNode;
    }

    public void remove(Node focusNode){
        Node parentNode = this.parent;
        Node toRemove = focusNode;

        //CASE 1: Node toRemove has no children
        if(toRemove.leftChild == null && toRemove.rightChild == null){
            if(toRemove == root){
                root = null;
            } else if(isLeftNode){
                parentNode.leftChild = null;
            } else {
                parentNode.rightChild = null;
            }

        //CASE 2: Node toRemove has no right child
        } else if(toRemove.rightChild == null){
            if(toRemove == root){
                root = toRemove.leftChild;
            } else if(isLeftNode){
                parentNode.leftChild = toRemove.leftChild;
            } else {
                parentNode.rightChild = toRemove.leftChild;
            }

        //CASE 3: Node toRemove has no left child
        } else if(toRemove.leftChild == null){
            if(toRemove == root){
                root = toRemove.rightChild;
            } else if(isLeftNode){
                parentNode.leftChild = toRemove.rightChild;
            } else {
                parentNode.rightChild = toRemove.rightChild;
            }

        //CASE 4: Node toRemove has a left and right child
        } else {
            Node replacementNode = getReplacementNode(toRemove);

            if(toRemove == root){
                root = replacementNode;
            } else if(isLeftNode){
                parentNode.leftChild = replacementNode;
            } else {
                parentNode.rightChild = replacementNode;
            }

        }
    }


    public Node getReplacementNode(Node toRemove){
        Node parentOfReplacement = toRemove;
        Node replacement = toRemove;
        Node focusNode = toRemove.rightChild; //We always replace nodes with the right child (larger value).

        while(focusNode != null){
            parentOfReplacement = replacement;
            replacement = focusNode;
            focusNode = focusNode.leftChild;
        }

        if(replacement != toRemove.rightChild){
            parentOfReplacement.leftChild = replacement.rightChild;
            replacement.rightChild = toRemove.rightChild;
        }

        return replacement;

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

