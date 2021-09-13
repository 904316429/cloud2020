package com.springcloud.test;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-09-08 18:40
 */
public class ClusterCanalClientTest extends  AbstractCanalClientTest{

    public ClusterCanalClientTest(String destination){
        super(destination);
    }

    // "192.168.87.151:2181" "example"
    public void initCanal(String zkIp, String destination){

        // 基于固定canal server的地址，建立链接，其中一台server发生crash，可以支持failover
        // CanalConnector connector = CanalConnectors.newClusterConnector(
        // Arrays.asList(new InetSocketAddress(
        // AddressUtils.getHostIp(),
        // 11111)),
        // "stability_test", "", "");

        // 基于zookeeper动态获取canal server的地址，建立链接，其中一台server发生crash，可以支持failover
        CanalConnector connector = CanalConnectors.newClusterConnector(zkIp, destination, "", "");

        final ClusterCanalClientTest clientTest = new ClusterCanalClientTest(destination);
        clientTest.setConnector(connector);
        clientTest.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                logger.info("## stop the canal client");
                clientTest.stop();
            } catch (Throwable e) {
                logger.warn("##something goes wrong when stopping canal:", e);
            } finally {
                logger.info("## canal client is down.");
            }
        }));
    }
}
