package lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.RetryNTimes;
import session.Client;

/**
 * @author 陈濛
 *
 * 分布式AtomicInt，实现分布式计数器
 */
public class AtomicInt_Demo {

    public static void main(String[] args) throws Exception {
        CuratorFramework client = Client.zkClient();
        String path = "/atomicint";

        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client, path, new RetryNTimes(3, 1000));
        AtomicValue<Integer> rc = atomicInteger.add(8);
        System.out.println("结果：" + rc.succeeded());
    }

}
