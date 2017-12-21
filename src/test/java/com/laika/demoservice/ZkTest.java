package com.laika.demoservice;

import com.laika.demoservice.client.ZooKeeperConnector;

import org.apache.curator.framework.CuratorFramework;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: 会跳舞的机器人
 * @date: 2017/12/7 14:01
 * @description:
 */
public class ZkTest extends Tester {
    @Autowired
    private ZooKeeperConnector zooKeeperConnector;

    @Test
    public void zkTest() {
        CuratorFramework curatorFramework = zooKeeperConnector.getClient();
        System.out.println("zookeeper状态:" + curatorFramework.getState());
    }
}
