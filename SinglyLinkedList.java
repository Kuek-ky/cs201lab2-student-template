import java.util.ArrayList;
import java.util.HashMap;

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
        if (isEmpty() || size() <= 1) return;

        Node<E> walk = head;

        ArrayList<Node<E>> numbers = new ArrayList<Node<E>>();
        ArrayList<Node<E>> original_numbers = new ArrayList<Node<E>>(); //keep og sequence
        HashMap<Node<E>, Integer> nodeMap = new HashMap<Node<E>, Integer>(); //map og index with node

        for (int i = 0; i < size(); i++){
            numbers.add(walk);
            original_numbers.add(walk);
            nodeMap.put(walk, i);
            walk = walk.getNext();
        }

        //get a sorted array
        numbers.sort(
            (Node<E> e1, Node<E> e2) -> e1.getElement().compareTo(e2.getElement())
        );

        int swapNum = size() / 2;

        for (int i = 0; i < swapNum; i++) {
            Node<E> small = numbers.get(i);
            Node<E> large = numbers.get(size() - i - 1);

            int indx_small = nodeMap.get(small);
            int indx_large = nodeMap.get(large);
            
            original_numbers.set(indx_small, large);
            original_numbers.set(indx_large, small);
        }

        for (int i = 0; i < original_numbers.size() - 1; i++) {
            original_numbers.get(i).setNext(original_numbers.get(i + 1));
        }

        head = original_numbers.get(0);
        tail = original_numbers.get(original_numbers.size() - 1);
        tail.setNext(null);
    }

   
}

