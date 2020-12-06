package leader;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import session.Client;

/**
 * @author 陈濛
 * @date 2020/12/6 11:11 下午
 */
public class LeaderElecetion_Demo {
    public static void main(String[] args) throws InterruptedException {
        String path = "/master";
        CuratorFramework client = Client.zkClient();

        LeaderSelector selector = new LeaderSelector(client, path, new LeaderSelectorListenerAdapter() {
            // 成为leader后的回调函数，执行完后会退出leader，重新进行选举（即删除对应的zk节点）
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.out.println("成为leader");
                Thread.sleep(3000);
                System.out.println("完成leader操作，释放leader权利");
            }
        });


        selector.autoRequeue();
        selector.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
