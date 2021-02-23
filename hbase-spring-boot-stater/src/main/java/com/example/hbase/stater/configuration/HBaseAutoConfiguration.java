package com.example.hbase.stater.configuration;

import com.example.hbase.stater.component.HBaseService;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.hbase.stater.configuration.HBaseProperties.PREFIX;

/**
 * HBase配置
 * @author jackie wang
 * @since 2021/2/1 17:27
 */
@Configuration
@EnableConfigurationProperties(HBaseProperties.class) // 激活配置属性
@ConditionalOnProperty(prefix = PREFIX, value = "zookeeper-quorum") // 条件加载配置属性，如果满足条件，实例化当前类
public class HBaseAutoConfiguration {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HBaseProperties properties;

    @Bean
    public HBaseService getHbaseService() {
        // 初始化hadoop home路径
        if(properties.getHadoopHome() !=null && properties.getHadoopHome().trim().length()>0) {
            System.setProperty("hadoop.home.dir", properties.getHadoopHome());
        } else {
            String hadoopHome = System.getenv("HADOOP_HOME");
            if (StringUtils.isNotBlank(hadoopHome)) {
                System.setProperty("hadoop.home.dir", hadoopHome);
            } else {
                throw new RuntimeException("The current project environment variable 'Hadoop Home' cannot be empty.");
            }
        }

        // configuration通过HBaseConfiguration.create()加装hbase-site.xml配置文件中的属性值
        org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", properties.getZookeeperQuorum());
        configuration.set("hbase.master.port", properties.getClientPort());
        configuration.set("hbase.rootdir", properties.getZnodeParent());
        return new HBaseService(configuration);
    }

}
