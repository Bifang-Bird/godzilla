package com.simaba.gateway.drive.config;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

/**
 * @author ：ywq
 * @date ：Created in 2019/11/7 13:56
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CassnadraConfig {

    public static String[] hosts = {"10.2.7.125","10.2.7.126","10.2.7.127","10.2.4.135","10.2.4.136","10.2.4.137","10.2.4.138","10.2.4.139"};
//    public static String[] hosts = {"192.168.1.131"};

      public static int port = 19042;//端口号

    public static String keyspace = "dpsspacef";//要连接的库


    public static Session getConnectSession(String name) {

        Cluster cluster = Cluster.builder()
                .addContactPoints(hosts)
                .withPort(port)
                .build();

//        Cluster cluster = Cluster.builder().addContactPoint("10.2.4.135").withPort(19042).build();
        Session session = cluster.connect(keyspace);

        return session;
    }
}
