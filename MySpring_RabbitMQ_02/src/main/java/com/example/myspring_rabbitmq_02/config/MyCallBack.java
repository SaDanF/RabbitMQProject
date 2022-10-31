//package com.example.myspring_rabbitmq_02.config;
//
//import com.sun.org.apache.xpath.internal.operations.String;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
////设置回调在消息发送者在消息发送失败之后设置的回调
////设置回退回来的消息
//@Slf4j
//@Component
//public class MyCallBack implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback{
////    下面写了回调的方法还需要进行注入
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @PostConstruct
//    public void init(){
////        进行注入
//        rabbitTemplate.setConfirmCallback(this);
//        rabbitTemplate.setReturnCallback(this);
//    }
//
//    /*交换机确认回调方法
//    * 1、发消息 交换机己收到了 回调
//    *   1、1correlationDate 保存回调接口的ID以及相关信息
//    *   1、2交换机收到消息 ack=true
//    *   1、3 cause null
//    * 2、发送消息 交换机接收失败了回调
//    *   2、1correlationDate 保存回调消息的ID及相关信息
//    *   2、2交换机收到消息 ack=false
//    *   2、3cause 失败的原因*/
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        String id=correlationData != null ?correlationData.getId() : "";
//        if(ack){
//            log.info("交换机已经收到id为：{}的消息",id);
//        }else{
//            log.info("交换机还未收到ID为{}的消息，由于原因{}",id,cause);
//        }
//    }
//
////    可以在当消息传递过程中不可以达到时将消息返回给生产者
////    只有不可到达目的地的时候才能进行回退
//    @Override
//    public void returnedMessage(Message message, int replyCod, String replyText, String exchange, String routingKey) {
//        log.error("消息{}，被交换机{}退回,退回原因{},路由key{}",
//                new String(message.getBody()),exchange,replyText,routingKey);
//    }
//}
