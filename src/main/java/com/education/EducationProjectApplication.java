package com.education;

import com.education.rabbitmq.consumer.FollowQueueConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class EducationProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(EducationProjectApplication.class, args);

        Thread followQueueConsumerThread = new Thread(new FollowQueueConsumer());
        followQueueConsumerThread.start();
    }
}
