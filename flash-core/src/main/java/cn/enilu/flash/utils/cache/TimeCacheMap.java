package cn.enilu.flash.utils.cache;

import cn.enilu.flash.utils.Maps;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by zt on 2017/4/12 0012.
 */
public class TimeCacheMap<K, V> {
    private static final int DEFAULT_NUM_BUCKETS = 3;


    /**
     * 回调函数实现这个接口就可以，至少可以把删掉的元素传回去
     * @param <K>
     * @param <V>
     */
    public static interface ExpiredCallback<K, V> {
        public void expire(K key, V val);
    }

    /**
     * 把数据分成多个桶，用链表是因为在头尾的增减操作时O（1）
     */
    private LinkedList<HashMap<K, V>> buckets;

    private final Object lock = new Object();
    private Thread cleaner;
    private ExpiredCallback callback;

    public TimeCacheMap(int expirationSecs, int numBuckets, ExpiredCallback<K, V> callback) {
        if (numBuckets < 2) {
            throw new IllegalArgumentException("numBuckets must be >= 2");
        }
        //构造函数中，按照桶的数量，初始桶
        buckets = new LinkedList<HashMap<K, V>>();
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new HashMap<K, V>());
        }


        this.callback = callback;
        final long expirationMillis = expirationSecs * 1000L;
        final long sleepTime = expirationMillis / (numBuckets - 1);
        cleaner = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Map<K, V> dead = null;
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {

                    }
//                        Time.sleep(sleepTime);
                    synchronized (lock) {
                        //删掉最后一个桶，在头补充一个新的桶，最后一个桶的数据是最旧的
                        dead = buckets.removeLast();
                        buckets.addFirst(Maps.newHashMap());
                    }
                    if (TimeCacheMap.this.callback != null) {
                        for (Map.Entry<K, V> entry : dead.entrySet()) {
                            TimeCacheMap.this.callback.expire(entry.getKey(), entry.getValue());
                        }
                    }
                }
            }
        });
        //作为守护线程运行，一旦主线程不在，这个线程自动结束
        cleaner.setDaemon(true);
        cleaner.start();
    }

    public TimeCacheMap(int expirationSecs, ExpiredCallback<K, V> callback) {
        this(expirationSecs, DEFAULT_NUM_BUCKETS, callback);
    }

    public TimeCacheMap(int expirationSecs) {
        this(expirationSecs, DEFAULT_NUM_BUCKETS);
    }

    public TimeCacheMap(int expirationSecs, int numBuckets) {
        this(expirationSecs, numBuckets, null);
    }


    public boolean containsKey(K key) {
        if (buckets.getFirst().containsKey(key)) {
            return true;
        } else {
            return false;
        }
    }

    public V get(K key) {
        return buckets.getFirst().get(key);
    }

    public void put(K key, V value) {
        buckets.getFirst().put(key, value);

    }

    public Object remove(K key) {
        return buckets.getFirst().remove(key);
    }

    public int size() {
        return buckets.getFirst().size();
    }

    /**
     * 中断清理线程中的sleep，_cleaner线程会抛出异常，然后_cleaner线程就死了，不再清理过期数据了
     * 调用了interrupt后，再跑sleep就会抛InterruptedException异常
     */
    public void neverCleanup() {
        cleaner.interrupt();

    }
}