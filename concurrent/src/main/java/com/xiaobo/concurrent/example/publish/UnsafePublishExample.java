package com.xiaobo.concurrent.example.publish;

import com.xiaobo.concurrent.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@NotThreadSafe
@Slf4j
public class UnsafePublishExample {


    private String[] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublishExample example = new UnsafePublishExample();
        log.info("states{}:",Arrays.toString(example.getStates()));

        example.getStates()[0]="d";
        log.info("states{}:",Arrays.toString(example.getStates()));

    }
}
