package org.example.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @description:
 * @author: yh
 * @date: 2022/9/5
 */
@RestController
@RequestMapping("/api")
public class JUCController {

    @GetMapping(value = "/cyclicBarrier")
    public void cyclicBarrier() {
        // 参与的线程数
        int threadNum = 5;
        //创建cyclicBarrier实例，定义barrierAction
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () ->
                System.out.println("已到全部通过栅栏")
        );
        //创建线程开始执行
        for (int i = 1; i <= threadNum; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 3; j++) {
                    try {
                        Random rand = new Random();
                        //随机一个2-5的整数
                        int randomNum = rand.nextInt(4) + 2;
                        Thread.sleep(randomNum * 1000);
                        System.out.println(Thread.currentThread().getName() + ", 通过了第" + j +
                                "道栅栏, " +
                                "使用了 " + randomNum + "s");
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }, "thread--" + i).start();
        }
    }
}
