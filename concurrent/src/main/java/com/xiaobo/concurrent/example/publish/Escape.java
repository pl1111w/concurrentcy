package com.xiaobo.concurrent.example.publish;

import com.xiaobo.concurrent.annotations.NotRecommend;
import com.xiaobo.concurrent.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int escape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}", Escape.this.escape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
