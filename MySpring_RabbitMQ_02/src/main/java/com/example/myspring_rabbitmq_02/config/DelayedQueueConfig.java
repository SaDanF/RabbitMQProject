package com.example.myspring_rabbitmq_02.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/*安装插件是用延时队列进行书写，可以避免消息延时*/
@Configuration
public class DelayedQueueConfig {
    //    队列
    public static final String DELAYED_QUEUE_NAME="delayed.queue.name";
    //    交换机
    public static final String DELAYED_EXCHANGE_NAME="delayed.exchange";
    //    routingKey
    public static final String DELAYED_ROUTING_KEY="delayed.routingkey";

    //    声明交换机 基于插件的
    @Bean
    public CustomExchange delayedExchange(){
        /*交换机的参数
         * 1、交换机名称
         * 2、交换机的类型
         * 3、是否需要持久化
         * 4、是否自动删除
         * 5、其他参数*/
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type","direct");
        return new CustomExchange(DELAYED_EXCHANGE_NAME,"x-delayed-message",
                false,false,arguments);
    }
    //    声明队列
    @Bean
    public Queue delayedQueue(){
        return new Queue(DELAYED_QUEUE_NAME);
    }

    //    绑定交换机和队列
    @Bean
    public Binding delayedQueueBindingDelayedExchange(@Qualifier("delayedQueue") Queue delayedQueue,
                                                      @Qualifier("delayedExchange")CustomExchange delayedExchange){
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(DELAYED_ROUTING_KEY).noargs();
    }

//    声明备份交换机 备份交换机只是多两个队列和一个交换机
//   写了备份交换机之后要告诉主交换机你的存在
//    @Bean
//    public DirectExchange confirmExchange(){
////                                            这是主交换机的名
//        return ExchangeBuilder.directExchange(DELAYED_EXCHANGE_NAME).durable(true)
////                             这个固定的写法          备份交换机的名称
//                .withArgument("alternate-exchange",DELAYED_EXCHANGE_NAME).build();
//    }

//    设置优先队列    意思是这只的5是最优先的队列 队列的优先顺序的是从大到小
//    需要在Rabbit的页面中设置Maximum priority 设置他的排序
//    AMQP.BasicProperties properties =
//        new AMQP.BasicProperties().builder().priority(5).build();

}
