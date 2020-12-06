package lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import session.Client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author 陈濛
 *
 * 分布式锁，生成全局唯一订单号
 */
public class Lock_Demo {
    public static void main(String[] args) {
        CuratorFramework client = Client.zkClient();
        String path = "/lock";

        final InterProcessMutex lock = new InterProcessMutex(client, path);
        final CountDownLatch down = new CountDownLatch(1);

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    down.await();
                    lock.acquire();
                } catch (Exception ignored) {}
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                String orderNo = sdf.format(new Date());
                System.out.println("生成订单号：" + orderNo);
                try {
                    lock.release();
                } catch (Exception ignore){}
            }).start();
        }

        down.countDown();
    }
}
