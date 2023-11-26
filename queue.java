import java.util.*;


public class Queue {
    private int maxSize; // Ukuran maksimum antrian
    private int[] queueArray; // Array untuk menyimpan elemen-elemen antrian
    private int front; // Indeks ujung depan antrian
    private int rear; // Indeks ujung belakang antrian
    private int currentSize; // Jumlah elemen dalam antrian

    public Queue(int size) {
        maxSize = size;
        queueArray = new int[maxSize];
        front = 0;
        rear = -1;
        currentSize = 0;
    }

    public void enqueue(int item) {
        if (!isFull()) {
            rear = (rear + 1) % maxSize;
            queueArray[rear] = item;
            currentSize++;
        } else {
            System.out.println("Antrian penuh, tidak dapat menambahkan elemen.");
        }
    }

    public int dequeue() {
        if (!isEmpty()) {
            int removedItem = queueArray[front];
            front = (front + 1) % maxSize;
            currentSize--;
            return removedItem;
        } else {
            System.out.println("Antrian kosong, tidak dapat menghapus elemen.");
            return -1; // Nilai kembalian yang menunjukkan antrian kosong
        }
    }

    public int front() {
        if (!isEmpty()) {
            return queueArray[front];
        } else {
            System.out.println("Antrian kosong, tidak ada elemen di ujung depan.");
            return -1; // Nilai kembalian yang menunjukkan antrian kosong
        }
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    public int size() {
        return currentSize;
    }
}

// Ini untuk mengeksekusi program

public class Main {
    public static void main(String[] args) {
        Queue q = new Queue(5);

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        System.out.println("Front: " + q.front()); // Output: 1

        q.dequeue();
        System.out.println("Front: " + q.front()); // Output: 2

        System.out.println("Is Empty: " + q.isEmpty()); // Output: false
        System.out.println("Size: " + q.size()); // Output: 2
    }
}
