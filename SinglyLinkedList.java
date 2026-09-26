import java.util.ArrayList;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here

    public void swap(){
        if (isEmpty() || size() <= 1) return ;

        //find largest and smallest
        Node<E> walk = head;

        ArrayList<E> numbers = new ArrayList<E>();

        for (int i = 0; i < size(); i++){
            numbers.add(walk.getElement());
            walk = walk.getNext();
        }

        //get a sorted array
        numbers.sort(
            (E e1, E e2) -> e1.compareTo(e2)
        );


        int swapNum = size() / 2;

        for (int i = 0; i < swapNum; i++) {
            Node<E> small = head;
            Node<E> large = head;


            Node<E> beforeSmall = null;
            Node<E> beforeLarge = null;
            E elementSmall = numbers.get(i);
            E elementLarge = numbers.get(size() - i - 1);
            // System.out.println("elementSmall: " + elementSmall);
            // System.out.println("elementlarge: " + elementLarge);

            for (int j = 0; j < size(); j++) {                
                if (!small.getElement().equals(elementSmall)) {
                    beforeSmall = small;
                    small = small.getNext();
                } 
                if (!large.getElement().equals(elementLarge)) {
                    beforeLarge = large;
                    large = large.getNext();
                } 
            }

            Node<E> nextSmall = small.getNext();
            Node<E> nextLarge = large.getNext();

            // System.out.println("Small: " + small.getElement());
            // System.out.println("Large: " + large.getElement());
            // // System.out.println("nextSmall: " + nextSmall.getElement());
            // // System.out.println("nextLarge: " + nextLarge.getElement());

            // System.out.println("a==========");
            // System.out.println("Small getnext: " + (small.getNext()  !=  null ? small.getNext().getElement() : "null"));
            // System.out.println("Large getnext: " + (large.getNext() !=  null ? large.getNext().getElement() : "null"));
            if (beforeLarge == null) {
                head = small;
                beforeSmall.setNext(large);

            } else if (beforeSmall == null) {
                head = large;
                beforeLarge.setNext(small);
            } else {
                beforeLarge.setNext(small);
                beforeSmall.setNext(large);
            }

            if (nextLarge == null) {
                tail = small;
            } else if (nextSmall == null) {
                tail = large;
            }

            if (nextSmall != null && nextSmall.equals(large)) {
                small.setNext(nextLarge);
                large.setNext(small);
            } else if (nextLarge != null && nextLarge.equals(small) ) {
                large.setNext(nextSmall);
                small.setNext(large);
            } else {
                large.setNext(nextSmall);
                small.setNext(nextLarge);
            }

            // System.out.println("a==========");
            // System.out.println("Small getnext: " + (small.getNext()  !=  null ? small.getNext().getElement() : "null"));
            // System.out.println("Large getnext: " + (large.getNext() !=  null ? large.getNext().getElement() : "null"));
            // System.out.println("a==========");
            // System.out.println("Small beforeLarge: " + (beforeSmall  !=  null ? beforeSmall.getElement() : "null"));
            // System.out.println("Large beforeLarge: " + (beforeLarge !=  null ? beforeLarge.getElement() : "null"));



            // System.out.println("a==========");
            // System.out.println("Small beforeLarge: " + (beforeSmall  !=  null ? beforeSmall.getElement() : "null"));
            // System.out.println("Large beforeLarge: " + (beforeLarge !=  null ? beforeLarge.getElement() : "null"));

            // System.out.println(this.toString());

        }
    }

   
}

