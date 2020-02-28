package com.xiaobo.concurrent.example.immutable;

import com.xiaobo.concurrent.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Maps;

import java.util.Collections;
import java.util.Map;

@Slf4j
@NotThreadSafe
public class immutableExample1 {
    private static Map<Integer, Integer> map = Maps.newHashMap(1, 1);

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        //map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1, 3);//会报错
        log.info("{}", map.get(1));
    }
}