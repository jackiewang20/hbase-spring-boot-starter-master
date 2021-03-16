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
    private String securityAuthentication;
    private String masterKerberosPrincipal;
    private String regionserverKerberosPrincipal;

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

    public String getSecurityAuthentication() {
        return securityAuthentication;
    }

    public void setSecurityAuthentication(String securityAuthentication) {
        this.securityAuthentication = securityAuthentication;
    }

    public String getMasterKerberosPrincipal() {
        return masterKerberosPrincipal;
    }

    public void setMasterKerberosPrincipal(String masterKerberosPrincipal) {
        this.masterKerberosPrincipal = masterKerberosPrincipal;
    }

    public String getRegionserverKerberosPrincipal() {
        return regionserverKerberosPrincipal;
    }

    public void setRegionserverKerberosPrincipal(String regionserverKerberosPrincipal) {
        this.regionserverKerberosPrincipal = regionserverKerberosPrincipal;
    }
}
