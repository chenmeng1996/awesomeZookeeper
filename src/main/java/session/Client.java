package session;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author 陈濛
 * @date 2020/12/5 4:18 下午
 */
public class Client {
    public static CuratorFramework zkClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", 5000, 3000, retryPolicy);
        client.start();
        client.usingNamespace("test");
        return client;
    }
}
