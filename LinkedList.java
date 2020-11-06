package com.company;

/**
 * Creates the LinkedList from scratch
 * @param <E>
 */
public class LinkedList<E> implements List<E> 
{

    Node<E> head;
    int size;

    // Constructor
    public LinkedList() 
    {
        head = null;
        size = 0;
    }

    // Node class
    private class Node<E> 
    {
        E data;
        Node<E> next;

        public Node(E value) 
        {
            data = value;
            next = null;
        }

    
    }

 
    public int size()
     {
        return this.size;
    }

    // Add item
    public boolean add(E item) 
    {
        if (head == null) 
        {
            head = new Node(item);
            ++size;
            return true;
        }

        Node prev = head;
        for (int i=1; i<size; i++) 
        {
            prev = prev.next;
        }

        Node node = new Node(item);
        prev.next = node;
        size++;
        return true;

    }

    // Add item at an index
    public void add(int pos, E item)
     {
        if (pos == 0) 
        {
            Node node = new Node(item);
            node.next = head;
            head = node;
            size++;
        } 
        else 
        {
            Node prev = head;
            for (int i=0; i<pos; i++) 
            {
                prev = prev.next;
            }
            Node node = new Node(item);
            size++;
        }
    }

    // Get item at an index
    public E get(int pos) 
    {
        Node curr = head;
        for (int i=0; i<pos; i++)
         {
            curr = curr.next;
        }
        return (E) curr.data;
    }

    // Remove an item at an index
    public E remove (int pos) 
    {
        if (pos == 0)
         {
            Node<E> node = head;
            head = head.next;
            --size;
            return node.data;
        } 
        else 
        {
            Node<E> prev = head;
            for (int i=0; i<pos-1; i++) 
            {
                prev = prev.next;
            }
            Node<E> node = prev.next;
            prev.next = node.next;
            --size;
            return node.data;
        }
    }

 



}