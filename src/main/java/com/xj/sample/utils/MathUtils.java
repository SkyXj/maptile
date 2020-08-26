package com.xj.sample.utils;

import java.util.Random;

/**
 * Copyright © 广州禾信仪器股份有限公司. All rights reserved.
 *
 * @Author hxsdd-20
 * @Date 2020/8/26 13:28
 * @Version 1.0
 */
public class MathUtils {
    public static Integer getRandom(Integer start,Integer end) {
        Random random=new Random();
        return (random.nextInt(end)+start);
    }
}
