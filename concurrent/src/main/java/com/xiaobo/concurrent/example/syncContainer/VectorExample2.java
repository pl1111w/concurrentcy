package com.xiaobo.concurrent.example.syncContainer;

import com.xiaobo.concurrent.annotations.NotThreadSafe;

import java.util.List;
import java.util.Vector;
@NotThreadSafe
public class VectorExample2 {

    public static void main(String[] args) {

        while (true){
            List<Integer> vector  = new Vector<>();

            for (int i=0;i<10;i++){
                vector.add(i);
            }

            Thread thread1 = new Thread(){
                public void run(){
                    for(int i=0;i<vector.size();i++){
                        vector.remove(i);
                    }
                }
            };
            Thread thread2 = new Thread(){
                public void run(){
                    for(int i=0;i<vector.size();i++){
                        vector.get(i);
                    }
                }
            };
            thread1.start();
            thread2.start();

        }


    }
}
