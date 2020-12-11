package lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import session.Client;

/**
 * 使用barrier方式1：没有线程数，需要手动唤醒线程
 * @author 陈濛
 */
public class Barrier_Dist_Demo {
    public static void main(String[] args) throws Exception {
        String path = "/barrier";

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                CuratorFramework client = Client.zkClient();
                DistributedBarrier barrier = new DistributedBarrier(client, path);
                try {
                    barrier.setBarrier();
                    barrier.waitOnBarrier();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(finalI + "启动");
            }).start();
        }

        Thread.sleep(2000);
        CuratorFramework client = Client.zkClient();
        DistributedBarrier barrier = new DistributedBarrier(client, path);
        // 移除barrier，唤醒阻塞在该barrier的线程
        barrier.removeBarrier();
    }
}
