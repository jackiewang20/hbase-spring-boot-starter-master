#引用hbase-spring-boot-starter组件使用说明

    hbase-spring-boot-starter组件当前默认支持hbase2.x版本。

##1.创建java hbase客户端消费者服务
###1.1.application.properties

spring.hbase.zookeeper-quorum=hadoop-server-001,hadoop-server-002,hadoop-server-003
spring.hbase.client-port=2181
spring.hbase.znode-parent=/hbase
# windows上测试Hadoop/YARN应用程序配置：配置winutils.exe存储目录一级路径
spring.hbase.hadoop-home=c:/hadoop

说明：根据hadoop集群/etc/hosts配置修改hbase属性配置。

###1.2.重命名hbase-spring-boot-starter包名注意事项
当前组件的自动装配全限定名为com.example.hbase.stater.configuration.HBaseAutoConfiguration，
如果需要重命名包名，需要同步更新hbase-spring-boot-stater\src\main\resources\META-INF\spring.factories
中的全限定名。

##2.客户端windows系统，配置hadoop集群IP主机映射
复制linux平台hadoop集群（hbase）hosts配置信息，添加到windows客户端主机hosts配置文件中。

###例如linux平台hadoop集群hosts配置为：
vi /etc/hosts
192.168.34.3  hadoop-server-001
192.168.34.4  hadoop-server-002
192.168.34.5  hadoop-server-003

复制以上配置，然后打开windows系统C:\windows\system32\drivers\etc\hosts文件，将hadoop (hbase)集群的ip地址粘贴配置到该文件中。

##3.运行eclipse中的hbase程序
可能会报找不到HADOOP_HOME环境变量，和缺少winutils.exe，报错。

###3.1.配置HADOOP_HOME
	右键点击“计算机”，属性，高级系统设置，高级，环境变量，系统变量配置如下：
	变量名：HADOOP_HOME
	变量值：D:\hadoop

###3.2.下载winutils.exe
	创建D:\hadoop\bin目录，将下载的winutils.exe文件粘贴到该目录中。
	下载地址：https://github.com/steveloughran/winutils

	根据当前hbase依赖的hadoop版本，选择相应的版本下载。

	然后运行hbase客户端程序。

###更多下载地址参考：
https://raw.githubusercontent.com/steveloughran/winutils/master/hadoop-2.7.1/bin/winutils.exe
https://raw.githubusercontent.com/steveloughran/winutils/master/hadoop-3.0.0/bin/winutils.exe


##4.Q&A
###4.1.Q:java.net.UnknownHostException: hadoop-server-001
A:
需要配置IP主机映射
复制linux平台hadoop集群（hbase）hosts配置，添加到windows客户端主机etc配置文件中。

###4.2.Q:
A:java.io.FileNotFoundException: java.io.FileNotFoundException: HADOOP_HOME and hadoop.home.dir are unset
A:
安装第3项配置。

更多参考：https://cwiki.apache.org/confluence/display/HADOOP2/WindowsProblems

