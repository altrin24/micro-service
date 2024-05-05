package com.cricket.simulator.matches.util;

import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class CommonUtils {

    public Integer getRandomNumber(Integer range){
        Random random=new Random();
        return random.nextInt(range);
    }
}
