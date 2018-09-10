public class BinaryTree {

    Node root;
    Node parent;
    Boolean isLeftNode = true;

    void addNode(String key, String name, int gender, int height) {
        Node newNode = new Node(key, name, gender, height);

        if (root == null) {
            root = newNode;
            newNode.setHeight(0);
        } else {
            Node focusNode = root;
            while (true) {
                Node parentNode = focusNode;
                if (key.compareTo(parentNode.key) < 0) { // key has a smaller value than parentNode i.e. key=A, parentNode=B
                    //GO LEFT
                    focusNode = focusNode.leftChild;
                    if (focusNode == null) {
                        parentNode.leftChild = newNode;
                        newNode.setHeight(parentNode.getHeight()+1);
                        return;
                    }
                } else {
                    //GO RIGHT
                    focusNode = focusNode.rightChild;
                    if (focusNode == null) {
                        parentNode.rightChild = newNode;
                        newNode.setHeight(parentNode.getHeight()+1);
                        return;
                    }
                }
            }
        }

        //REBALANCE
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
                toRemove.leftChild.setHeight(toRemove.leftChild.getHeight() + 1);
            } else {
                parentNode.rightChild = toRemove.leftChild;
                toRemove.leftChild.setHeight(toRemove.leftChild.getHeight() + 1);
            }

        //CASE 3: Node toRemove has no left child
        } else if(toRemove.leftChild == null){
            if(toRemove == root){
                root = toRemove.rightChild;
            } else if(isLeftNode){
                parentNode.leftChild = toRemove.rightChild;
                toRemove.rightChild.setHeight(toRemove.rightChild.getHeight() + 1);
            } else {
                parentNode.rightChild = toRemove.rightChild;
                toRemove.rightChild.setHeight(toRemove.rightChild.getHeight() + 1);

            }

        //CASE 4: Node toRemove has a left and right child
        } else {
            Node replacementNode = getReplacementNode(toRemove);

            if(toRemove == root){
                root = replacementNode;
            } else if(isLeftNode){
                replacementNode.setHeight(parentNode.leftChild.getHeight());
                parentNode.leftChild = replacementNode;

            } else {
                replacementNode.setHeight(parentNode.rightChild.getHeight());
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

    //BALANCING METHODS

    //CASE 1: Left Heavy - Outside Inbalance
    //CASE 2: Left Heavy - Inside Inbalance
    //CASE 3: Right Heavy - Outisde Inbalance
    //CASE 4: Right Heavy - Inside Inbalance

    //FINDING VALUES LESS THAN END VALE.
    int rank(Node vertex, String endValue){
        Node middleNode = vertex;
        if(middleNode == null){
            return 0;
        } else if(middleNode.key.equals(endValue)){
            return 1 + sizeOfSubtree();
        } else if(middleNode.key.compareTo(endValue) < 0){ //key is smaller than endValue
            //GO LEFT
            return 1 + rank(middleNode.rightChild, endValue);
        } else {
            //GO RIGHT
            return sizeOfSubtree() + rank(middleNode.rightChild, endValue);

        }
    }

    int sizeOfSubtree(){
        if(root != null){
            int leftSubtreeSize = root.getLeftSubtreeSize();
            return leftSubtreeSize;
        } else {
            return 0;
        }
    }
}


class Node {
    String key;
    String name;
    int gender;
    int height;

    Node leftChild;
    Node rightChild;

    public Node(String key, String name, int gender, int height) {
        this.key = key;
        this.name = name;
        this.gender = gender;
        this.height = height;
    }

    public String getName() {
        return this.name;
    }

    public void setHeight(int newHeight) {
        this.height = newHeight;
    }

    public int getHeight(){
        return this.height;
    }

    public int getLeftSubtreeSize(){
        int leftSize = 0;

        if(this.leftChild != null){
            leftSize = this.getLeftSubtreeSize();
        }

        return leftSize;
    }
}

