package watcher;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.CreateMode;
import session.Client;

/**
 * @author 陈濛
 *
 * 只监听子节点的变更
 */
public class PathChildrenCache_Demo {
    public static void main(String[] args) throws Exception {
        CuratorFramework client = Client.zkClient();
        String path = "/zk-book1";

        PathChildrenCache cache = new PathChildrenCache(client, path, true);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener((c, event) -> {
            switch (event.getType()) {
                case CHILD_ADDED:
                    System.out.println("添加了子节点：" + event.getData().getPath());
                    break;
                case CHILD_UPDATED:
                    System.out.println("更新了子节点：" + event.getData().getPath());
                    break;
                case CHILD_REMOVED:
                    System.out.println("删除了子节点：" + event.getData().getPath());
                    break;
            }
        });

        client.create().withMode(CreateMode.PERSISTENT).forPath(path);
        Thread.sleep(1000);

        client.create().withMode(CreateMode.PERSISTENT).forPath(path + "/c1");
        Thread.sleep(1000);

        client.delete().forPath(path + "/c1");
        Thread.sleep(1000);

        client.delete().forPath(path);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
