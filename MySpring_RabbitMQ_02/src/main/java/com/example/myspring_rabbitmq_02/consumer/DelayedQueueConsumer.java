package com.example.myspring_rabbitmq_02.consumer;


import com.example.myspring_rabbitmq_02.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

//消费者   基于插件的延迟
@Slf4j
@Component
public class DelayedQueueConsumer {
    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE_NAME)
    public void receiveDelayQueue(Message message){
        String msg =  new String(message.getBody());
        log.info("当前时间：{}，收到的延迟队列消息：{}",new Date().toString(),msg);
    }
}
