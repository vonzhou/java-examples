# Note






### 报错

> WARN DefaultEventHandler:89 - Failed to send producer request with correlation id 11 to broker 0 with data for partitions [test,0]

解决：

修改kafka的配置，设置下列属性为broker所在机器的IP地址。

```bash
# Hostname the broker will advertise to producers and consumers. If not set, it uses the
# value for "host.name" if configured.  Otherwise, it will use the value returned from
# java.net.InetAddress.getCanonicalHostName().
advertised.host.name=10.165.124.132
```