
/**
* @Title: MySchedule.java
* @Package com.zhidian.test
* @Description: TODO(用一句话描述该文件做什么)
* @author dongneng
* @date 2017-3-19 下午7:38:01
* @version V1.0
*/
package com.zhidian.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MySchedule
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dongneng
 * @date 2017-3-19 下午7:38:01
 *
 */
//@Component
public class MySchedule {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    //每3秒执行一次
    @Scheduled(fixedRate = 3000)
    public void timerRate() {
        System.out.println(sdf.format(new Date()));
    }

    //第一次延迟1秒执行，当执行完后3秒再执行
    @Scheduled(initialDelay = 1000, fixedDelay = 3000)
    public void timerInit() {
        System.out.println("init : "+sdf.format(new Date()));
    }
}
