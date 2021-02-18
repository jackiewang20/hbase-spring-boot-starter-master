package com.example.hbase.stater.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.example.hbase.stater.configuration.HBaseProperties.PREFIX;

/**
 * @author jackie wang
 * @since 2021/2/1 17:37
 */
@ConfigurationProperties(PREFIX)
public class HBaseProperties {
    public static final String PREFIX = "spring.hbase";

    private String zookeeperQuorum;
    private String clientPort;
    private String znodeParent;
    private String hadoopHome;

    public String getZookeeperQuorum() {
        return zookeeperQuorum;
    }

    public void setZookeeperQuorum(String zookeeperQuorum) {
        this.zookeeperQuorum = zookeeperQuorum;
    }

    public String getClientPort() {
        return clientPort;
    }

    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }

    public String getZnodeParent() {
        return znodeParent;
    }

    public void setZnodeParent(String znodeParent) {
        this.znodeParent = znodeParent;
    }

    public String getHadoopHome() {
        return hadoopHome;
    }

    public void setHadoopHome(String hadoopHome) {
        this.hadoopHome = hadoopHome;
    }
}
