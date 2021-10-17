package com.pl1111w.concurrent.example.threadTest;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2021/10/17 11:01
 */
public class WaitNotifyDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket(0);
        ProducerThread thread1 = new ProducerThread(ticket);
        CustomerThread thread2 = new CustomerThread(ticket);
        thread1.setName("Producer_");
        thread2.setName("Customer_");
        thread1.start();
        thread2.start();

    }
}

class ProducerThread extends Thread {

    private Ticket ticket;

    public ProducerThread(Ticket ticket) {
        this.ticket = ticket;
    }


    @Override
    public void run() {
        System.out.println("开始生产.......");
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticket.addNumber();
        }
    }
}

class CustomerThread extends Thread {

    private Ticket ticket;

    public CustomerThread(Ticket ticket) {
        this.ticket = ticket;
    }


    @Override
    public void run() {
        System.out.println("开始消费.......");
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticket.increaseNumber();
        }

    }
}

class Ticket {
    int number;

    Ticket(int number) {
        this.number = number;
    }

    public synchronized void addNumber() {

        if (number < 10) {
            number++;
            System.out.println(Thread.currentThread().getName() + " " + number);
            notify();

        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void increaseNumber() {

        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + " " + number);
            number--;
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
