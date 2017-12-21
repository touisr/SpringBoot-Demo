package com.laika.demoservice;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * @author: 会跳舞的机器人
 * @date: 2017/12/7 14:05
 * @description:
 */
public class RabbitMQTest extends Tester {
    // 交换器名称
    private static final String EXCHANGE = "EXCHANGE.ORDER_CREATE";
    // 消息内容
    private static String msg = "create order success";

    @Autowired
    ConnectionFactory connectionFactory;

    @Test
    public void mqTest() {
        Connection connection;
        Channel channel;
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            // 持久化
            boolean durable = true;
            // topic类型
            String type = "topic";
            // 声明交换器，如果交换器不存在则创建之
            channel.exchangeDeclare(EXCHANGE, type, durable);

            String messgeId = UUID.randomUUID().toString();
            // deliveryMode>=2表示设置消息持久化
            AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().deliveryMode(2).messageId(messgeId).build();
            // 发布消息
            String routingKey = "order_create";
            channel.basicPublish(EXCHANGE, routingKey, props, msg.getBytes("utf-8"));
            System.out.println("消息发送成功......");
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
