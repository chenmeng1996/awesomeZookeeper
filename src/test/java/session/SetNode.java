package session;

import org.apache.curator.framework.CuratorFramework;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 陈濛
 * @date 2020/12/5 4:51 下午
 */
public class SetNode {

    CuratorFramework client;

    @Before
    public void init() {
        client = Client.zkClient();
    }

    // 更新一个节点的数据
    @Test
    public void set1() throws Exception {
        client.setData().forPath("/test");
    }

    // 更新一个节点的数据，强制指定版本进行更新（CAS）
    // 版本通常从旧的stat对象中获取
    @Test
    public void set2() throws Exception {
        client.setData().withVersion(1).forPath("/test");
    }
}
