package com.example.myspring_rabbitmq_02.controller;

import com.example.myspring_rabbitmq_02.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/*发送延时消息*/
@Slf4j      //打印日志
@RestController
public class SendMsgController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/senDelayedMag/{message}/{delayedTime}")
    public void senDelayedMag(@PathVariable String message,
                              @PathVariable Integer delayedTime) {
        log.info("当前时间：{},发送了一个时间长{}ms的消息给队列delayed queue{}", new Date().toString(), delayedTime, message);
//        设置回调的方案在最后一个参数
        CorrelationData correlationData= new CorrelationData("1");
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE_NAME,
                DelayedQueueConfig.DELAYED_ROUTING_KEY, message, mes -> {
                    mes.getMessageProperties().setDelay(delayedTime);
                    return mes;
                },correlationData);
    }
}
