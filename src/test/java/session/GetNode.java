package session;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 陈濛
 * @date 2020/12/5 4:49 下午
 */
public class GetNode {

    CuratorFramework client;

    @Before
    public void init() {
        client = Client.zkClient();
    }

    // 读取一个节点的数据
    @Test
    public void get1() throws Exception {
        client.getData().forPath("/test");
    }

    // 读取一个节点的数据和stat
    @Test
    public void get2() throws Exception {
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath("/test");
    }
}
