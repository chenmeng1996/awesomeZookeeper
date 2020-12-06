package session;

import org.apache.curator.framework.CuratorFramework;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 陈濛
 * @date 2020/12/5 4:44 下午
 */
public class DeleteNode {

    CuratorFramework client;

    @Before
    public void init() {
        client = Client.zkClient();
    }

    // 删除叶子节点
    @Test
    public void delete1() throws Exception {
        client.delete().forPath("/test");
    }

    // 删除节点，并递归删除所有子节点
    @Test
    public void delete2() throws Exception {
        client.delete().deletingChildrenIfNeeded().forPath("/test");
    }

    // 删除节点，强制指定版本进行删除
    @Test
    public void delete3() throws Exception {
        client.delete().withVersion(1).forPath("/test");
    }

    // 删除节点，强制保证删除
    // 只要客户端会话有效，那么curator会在后台持续进行删除操作，直到删除成功
    @Test
    public void delete4() throws Exception {
        client.delete().guaranteed().forPath("/test");
    }
}
