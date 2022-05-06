package zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author cqp
 * @version 1.0.0
 * @ClassName CuratorWatchTest.java
 * @Description
 *
 * Zookeeper 提供了三种 watcher:
 *
 *  NodeCache: 监听某一个特定的节点
 *  PathChildrenCache: 监控一个 Znode 的子节点
 *  TreeCache: 监控整个树上的所有节点
 *
 * @createTime 2021年11月10日 21:52:00
 */
public class CuratorWatchTest {
    CuratorFramework client;
    @Before
    public void testConnect(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 10);

        //client = CuratorFrameworkFactory.newClient("47.110.154.185:2181", 60 * 1000, 15 * 1000, retryPolicy);
        client = CuratorFrameworkFactory.builder()
                .connectString("47.110.154.185:2181")
                .sessionTimeoutMs(60*1000)
                .connectionTimeoutMs(15* 1000)
                .retryPolicy(retryPolicy)
                .namespace("cqp")
                .build();
        client.start();
    }

    @After
    public void close(){
        if(client != null){
            client.close();
        }
    }

    /**
     *  curator 5.x 版本废弃了 NodeCache 而是使用了 CutatorCache 代替
     *
     */
    @Test
    public void testNodeCache() throws Exception {
        // 1. 创建 NodeCache 对象
        final NodeCache nodeCache = new NodeCache(client, "/app1");

        // 2. 注册监听
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("节点变化了~");
                byte[] data = nodeCache.getCurrentData().getData();
                System.out.println(new String(data));
            }
        });
        nodeCache.start(true);
        while(true){

        }
    }

    /**
     * 演示: PathChildrenCache 监听某个节点的所有子节点们
     * @throws Exception
     */
    @Test
    public void testPathChildrenCache() throws Exception{
        // 1. 创建监听对象
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/app2", true);

        //2. 绑定监听器
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
                System.out.println("子节点变化了~");
                System.out.println(event);

                // 3. 获取事件类型
                PathChildrenCacheEvent.Type type = event.getType();

                // 判断事件是否是更新数据
                if(type.equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)){
                    System.out.println("有数据更新");
                    byte[] data = event.getData().getData();
                    System.out.println(new String(data));
                }
            }
        });

        // 4. 开启监听
        pathChildrenCache.start();

        while (true){}

    }



    /**
     * 演示: TreeCache 监听某个节点自己和所有子节点们
     * @throws Exception
     */
    @Test
    public void testTreeCache() throws Exception{
        TreeCache treeCache = new TreeCache(client, "/app2");

        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent event) throws Exception {
                System.out.println("节点变化了！");
                System.out.println(event);
         }
        });

        treeCache.start();
        while (true){}
    }



}
