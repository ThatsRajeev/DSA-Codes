public class LL {
    static Node head;
    private int size;

    LL(){
        this.size = 0;
    }
    public class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
            size++;
        }
    }
    public void addFirst(String data) {
        Node newNode = new Node(data);
        if(head == null) {
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void addLast(String data) {
        Node newNode = new Node(data);
        if(head == null) {
            head = newNode;
            return;
        }
        Node currNode = head;
        while(currNode.next != null) {
            currNode = currNode.next;
        }
        currNode.next = newNode;
    }

    public void printList() {
        if(head == null) {
            System.out.println("list is empty");
            return;
        }
        Node currNode = head;
        while(currNode != null) {
            System.out.print(currNode.data + " -> ");
            currNode = currNode.next;
        }
        System.out.println("NULL");
    }

    public void remove(String data) {
        if(head == null) {
            System.out.println("List is empty");
        } else {
            Node curNode = head;
            Node prevNode = null;
            while(curNode != null) {
                if(curNode.data.equals(data)) {
                    if(head.data == data) {
                        head = curNode.next;
                    } else {
                        prevNode.next = curNode.next;
                    }
                    size--;
                }
                prevNode = curNode;
                curNode = curNode.next;
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void reverseIterate() {
        if(head == null || head.next == null) {
            return;
        }
        Node prevNode = head;
        Node currNode = head.next;

        while(currNode!=null) {
            Node nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }
        head.next = null;
        head = prevNode;
    }

    public void reverseRecursive(Node prevNode, Node currNode) {
        if (currNode == null) {
            head.next = null;
            head = prevNode;
            return;
        }
        Node nextNode = currNode.next;
        currNode.next = prevNode;
        reverseRecursive(currNode, nextNode);

    }

    public static void main(String args[]) {
        LL list = new LL();
        // list.addFirst("a");
        list.addFirst("is");
        list.printList();

        // list.addLast("list");
        // list.printList();

        // list.addFirst("This");
        // list.printList();

        list.remove("is");
        list.printList();

        // System.out.println(list.getSize());

        // list.reverseRecursive(head, head.next);
        // list.printList();

        // LinkedList<String> newList = new LinkedList<String>();

        // newList.addFirst("a");
        // newList.addFirst("is");
        // System.out.println(newList);

        // newList.addLast("this");
        // System.out.println(newList);
        // System.out.println(newList.size());

        // for (int i=0; i<newList.size(); i++) {
        //     System.out.print(newList.get(i) + "->");
        // }

        // newList.removeFirst();
        // System.out.println(newList);

        // newList.remove(1);
        // System.out.println(newList);
    }
}
