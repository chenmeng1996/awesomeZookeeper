package lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import session.Client;

/**
 * 使用barrier方式2：阻塞线程数满足，自发唤醒
 * @author 陈濛
 */
public class Barrier_Dist_Demo_2 {
    public static void main(String[] args) throws Exception {
        String path = "/barrier";

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                CuratorFramework client = Client.zkClient();
                DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(client, path, 3);
                try {
                    barrier.enter();
                    Thread.sleep(1000);
                    barrier.leave();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(finalI + "启动");
            }).start();
        }

    }
}
