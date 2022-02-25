package ru.job4j.linked;

public class Node<T> {
    private Node<T> next;
    private T value;

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = new Node<T>().next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = new Node<T>().value;
    }
}
