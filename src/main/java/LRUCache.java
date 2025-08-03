import java.util.HashMap;

class LRUCache {
    class Node {
        Node next;
        Node prev;
        int key;
        int val;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    int cap = 0 ;
    Node head;
    Node tail;
    HashMap<Integer, Node> map = new HashMap<>();
    public LRUCache(int capacity) {
        this.cap = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        int currVal = map.get(key).val;
        removeNode(key);
        addNode(key,currVal);
        return currVal;

    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            removeNode(key);
            cap++;
        }

        if(cap<=0){
            removeNode(tail.prev.key);
            cap++;
        }

        addNode(key,value);
        cap--;
    }

    public void addNode(int key, int value){
        Node newNode = new Node(key,value);
        Node currHeadNode = head.next;
        head.next = newNode;
        newNode.next = currHeadNode;
        newNode.prev = head;
        currHeadNode.prev = newNode;
        map.put(key,newNode);
    }

    public void removeNode(int key){
        Node curNode = map.get(key);
        Node prevNode = curNode.prev;
        Node nextNode = curNode.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        map.remove(key);
    }
}
