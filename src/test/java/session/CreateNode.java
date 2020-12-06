package session;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 陈濛
 * @date 2020/12/5 4:33 下午
 */
public class CreateNode {

    CuratorFramework client;

    @Before
    public void init() {
        client = Client.zkClient();
    }

    // 创建一个空节点，初始内容为空
    @Test
    public void create1() throws Exception {
        client.create().forPath("/test");
    }

    // 创建一个带内容的节点
    @Test
    public void create2() throws Exception {
        client.create().forPath("/test2", "init".getBytes());
    }

    // 创建一个空临时节点
    @Test
    public void create3() throws Exception {
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/test3");
    }

    // 创建一个空临时节点，自动创建父节点（持久节点）
    @Test
    public void create4() throws Exception {
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/test4/a/b");
    }
}
