package watcher;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.zookeeper.CreateMode;
import session.Client;

/**
 * @author 陈濛
 *
 * 监听节点的创建和更新
 */
public class NodeCache_Demo {

    public static void main(String[] args) throws Exception {
        CuratorFramework client = Client.zkClient();
        String path = "/zk-book/nodecache";

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .forPath(path, "init".getBytes());

        final NodeCache cache = new NodeCache(client, path, false);
        cache.start(true);
        cache.getListenable().addListener(() -> {
            System.out.println("节点数据已更新，新数据：" + new String(cache.getCurrentData().getData()));
        });

        client.setData().forPath(path, "u".getBytes());
        Thread.sleep(1000);

        client.delete().deletingChildrenIfNeeded().forPath(path);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
