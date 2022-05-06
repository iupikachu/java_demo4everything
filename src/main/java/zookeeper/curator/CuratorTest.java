package zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName CuratorTest.java
 * @Description java api 操作 zookeeper
 * @createTime 2021年11月10日 19:22:00
 */
public class CuratorTest {
    CuratorFramework client;
    @Before
    public void testConnect(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 10);

        //client = CuratorFrameworkFactory.newClient("47.110.154.185:2181", 60 * 1000, 15 * 1000, retryPolicy);
        client = CuratorFrameworkFactory.builder()
                        .connectString("47.110.154.185:2181")
                        .sessionTimeoutMs(60*1000)
                        .connectionTimeoutMs(15*1000)
                        .retryPolicy(retryPolicy)
                        .namespace("cqp")
                        .build();
       client.start();


    }

    @Test
    public void testCreate1() throws Exception {
        // 基本创建1
        String path = client.create().forPath("/app1");
        System.out.println(path);
    }

    @Test
    public void testCreate2() throws Exception {
        // 基本创建2 数据存储
        String path = client.create().forPath("/app3","meme".getBytes());
        System.out.println(path);
    }

    @Test
    public void testCreate3() throws Exception {
        // 基本创建3 数节点类型
        // 默认是 持久化                                     临时节点
        String path = client.create().withMode(CreateMode.EPHEMERAL).forPath("/app3");
        System.out.println(path);
    }

    @Test
    public void testCreate4() throws Exception {
        // 基本创建4 创建多级节点
        // 默认是 持久化                                     临时节点
        String path = client.create().creatingParentContainersIfNeeded().forPath("/app4/p1");
        System.out.println(path);
    }

    /**
     * 查询节点
     * 1. 查询数据: get
     * 2. 查询子节点：ls
     * 3. 查询节点状态信息: ls -s
     */
    @Test
    public void testGet1() throws Exception {
        //1. 查询数据: get
        byte[] data = client.getData().forPath("/app1");
        System.out.println(new String(data));
    }


    @Test
    public void testGet2() throws Exception {
        //2. 查询子节点: ls
        List<String> path = client.getChildren().forPath("/");
        System.out.println(path);
    }

    @Test
    public void testGet3() throws Exception {
        //3. 查询节点状态信息 ls -s
        Stat status = new Stat();
        System.out.println(status);
        client.getData().storingStatIn(status).forPath("/app1");
        System.out.println(status);
    }

    /**
     * 修改数据
     * 1.修改数据
     * 2.根据版本修改数据
     */
    @Test
    public void testSet() throws Exception {
        // 修改数据
        client.setData().forPath("/app1","iu".getBytes());
    }

    @Test
    public void testSetForVersion() throws Exception {
        // 修改数据
        Stat status = new Stat();
        client.getData().storingStatIn(status).forPath("/app1");
        int version = status.getVersion();
        client.setData().withVersion(version).forPath("/app1","hehe".getBytes());
    }


    /**
     * 删除节点
     * 1. delete 删除单个节点
     * 2. deleteall 删除带有子节点的节点
     * 3. 必须成功的删除
     * 4. 回调
     */

    @Test
    public void testDelete1() throws Exception{
        //1. 删除单个节点
        client.delete().forPath("/app1");
    }

    @Test
    public void testDelete2() throws Exception{
        //2. 删除带有子节点的节点
        client.delete().deletingChildrenIfNeeded().forPath("/app4");
    }

    @Test
    public void testDelete3() throws Exception{
        //3. 由于网络原因可能导致删除的请求发送不到服务端的zookeeper
        // 失败就重发，直到删除成功为止
        client.delete().guaranteed().forPath("/app4");
    }

    @Test
    public void testDelete4() throws Exception{
        // 4. 删除后，回调函数
        client.delete().guaranteed().inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println("我被删除了！");
                System.out.println(curatorEvent);
            }
        }).forPath("/app1");
    }

    @Test
    public void testExists() throws Exception {
        if(client.checkExists().forPath("/app1:") == null){
            System.out.println("不存在该节点");
        }
    }


    @After
    public void close(){
        if(client != null){
            client.close();
        }
    }



}
