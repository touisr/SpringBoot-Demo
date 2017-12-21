package com.laika.demoservice.utils;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

//@Service
public class RabbitMQUtils {
    static Logger logger = LoggerFactory.getLogger(RabbitMQUtils.class);

    @Autowired
    ConnectionFactory connectionFactory;

    Connection conn;

    // 发送消息
    public void send(String queue, String exchange, String routingKey, JSONObject json) {
        try {
            if (conn == null) {
                conn = connectionFactory.newConnection();
            }
            Channel channel = conn.createChannel();
            String msg = json.toJSONString();
            channel.queueDeclare(queue + "_" + routingKey, false, false, false, null);
            channel.queueBind(queue + "_" + routingKey, exchange, routingKey);
            topicExchangeDeclare(exchange);
            channel.basicPublish(exchange, routingKey, null, msg.getBytes("utf-8"));
            channel.close();
        } catch (IOException e) {
            logger.error("IOException:", e);
        } catch (TimeoutException e) {
            logger.error("TimeoutException:", e);
        }
    }

    // 发送消息Api
    public void sendApi(String queue, String exchange, String routingKey, JSONObject json) {
        try {
            if (conn == null) {
                conn = connectionFactory.newConnection();
            }
            Channel channel = conn.createChannel();
            String msg = json.toJSONString();
            topicExchangeDeclare(exchange);
            channel.queueDeclare(queue, false, false, false, null);
            channel.queueBind(queue, exchange, routingKey);
            channel.basicPublish(exchange, routingKey, null, msg.getBytes("utf-8"));
            channel.close();
        } catch (IOException e) {
            logger.error("IOException:", e);
        } catch (TimeoutException e) {
            logger.error("TimeoutException:", e);
        }
    }

    // fanout方式发送消息
    public void sendWithFanout(String exchange, JSONObject json) {
        try {
            if (conn == null) {
                conn = connectionFactory.newConnection();
            }
            Channel channel = conn.createChannel();
            String msg = json.toJSONString();
            fanoutExchangeDeclare(exchange);
            channel.basicPublish(exchange, "", null, msg.getBytes("utf-8"));
            channel.close();
        } catch (IOException e) {
            logger.error("IOException:", e);
        } catch (TimeoutException e) {
            logger.error("TimeoutException:", e);
        }
    }

    // 绑定
    public void bind(String queue, String exchange, String routingKey) {
        try {
            if (conn == null) {
                conn = connectionFactory.newConnection();
            }
            Channel channel = conn.createChannel();
            channel.exchangeDeclare(exchange, "topic", true);

            // 绑定
            channel.queueBind(queue, exchange, routingKey);

            channel.close();
        } catch (IOException e) {
            logger.error("IOException:", e);
        } catch (TimeoutException e) {
            logger.error("TimeoutException:", e);
        }

    }

    // 声明队列
    public void queueDeclare(String queue) {
        try {
            if (conn == null) {
                conn = connectionFactory.newConnection();
            }
            Channel channel = conn.createChannel();

            // 声明队列，如果队列不存在则创建之
            boolean durable = false;
            boolean exclusive = false;
            boolean autoDelete = false;
            Map<String, Object> arguments = null;
            channel.queueDeclare(queue, durable, exclusive, autoDelete, arguments);

            channel.close();
        } catch (IOException e) {
            logger.error("IOException:", e);
        } catch (TimeoutException e) {
            logger.error("TimeoutException:", e);
        }
    }

    // 声明direct交换器
    public void directExchangeDeclare(String exchange) {
        String type = "direct";
        exchangeDeclare(exchange, type);
    }

    // 声明fanout交换器
    public void fanoutExchangeDeclare(String exchange) {
        String type = "fanout";
        exchangeDeclare(exchange, type);
    }

    // 声明topic交换器
    public void topicExchangeDeclare(String exchange) {
        String type = "topic";
        exchangeDeclare(exchange, type);
    }

    private void exchangeDeclare(String exchange, String type) {
        try {
            if (conn == null) {
                conn = connectionFactory.newConnection();
            }
            Channel channel = conn.createChannel();

            // 声明交换器
            channel.exchangeDeclare(exchange, type, true);

            channel.close();
        } catch (IOException e) {
            logger.error("IOException:", e);
        } catch (TimeoutException e) {
            logger.error("TimeoutException:", e);
        }
    }

}
