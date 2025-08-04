class Node {
    int key;
    int val;
    Node prev;
    Node next;
    
    Node (int key, int val){
        this.key = key;
        this.val = val;
        prev = null;
        next = null;
    }
}

class LRUCache {
    Node head;
    Node tail;
    int capacity;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap();
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            removeNode(map.get(key));
            Node newNode = addNode(key, map.get(key).val);
            map.put(key, newNode);
            return map.get(key).val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            removeNode(map.get(key));
        }
        else {
            if(map.size() >= capacity){
                removeTail();
            }
        }
        Node newNode = addNode(key, value);
        map.put(key, newNode);
    }

    public Node addNode(int key, int val){
        Node current = new Node(key, val);
        Node temp = head.next;
        current.prev = head;
        current.next = temp;
        temp.prev = current;
        head.next = current;
        return current;
    }

    public void removeNode(Node current){
        Node prevNode = current.prev;
        Node nextNode = current.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    public void removeTail(){
        Node prevTail = tail.prev;
        prevTail.prev.next = tail;
        tail.prev = prevTail.prev;
        map.remove(prevTail.key);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
