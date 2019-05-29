package cn.enilu.flash.utils.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by zt on 2017/4/12 0012.
 */
public class TimeCacheMap<K, V> {
    private static final int DEFAULT_NUM_BUCKETS = 3;


    //回调函数实现这个接口就可以，至少可以把删掉的元素传回去
    public static interface ExpiredCallback<K, V> {
        public void expire(K key, V val);
    }

    //把数据分成多个桶，用链表是因为在头尾的增减操作时O（1）
    private LinkedList<HashMap<K, V>> _buckets;

    private final Object _lock = new Object();
    private Thread _cleaner;
    private ExpiredCallback _callback;

    public TimeCacheMap(int expirationSecs, int numBuckets, ExpiredCallback<K, V> callback) {
        if (numBuckets < 2) {
            throw new IllegalArgumentException("numBuckets must be >= 2");
        }
        //构造函数中，按照桶的数量，初始桶
        _buckets = new LinkedList<HashMap<K, V>>();
        for (int i = 0; i < numBuckets; i++) {
            _buckets.add(new HashMap<K, V>());
        }


        _callback = callback;
        final long expirationMillis = expirationSecs * 1000L;
        final long sleepTime = expirationMillis / (numBuckets - 1);
        _cleaner = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Map<K, V> dead = null;
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {

                    }
//                        Time.sleep(sleepTime);
                    synchronized (_lock) {
                        //删掉最后一个桶，在头补充一个新的桶，最后一个桶的数据是最旧的
                        dead = _buckets.removeLast();
                        _buckets.addFirst(new HashMap<K, V>());
                    }
                    if (_callback != null) {
                        for (Map.Entry<K, V> entry : dead.entrySet()) {
                            _callback.expire(entry.getKey(), entry.getValue());
                        }
                    }
                }
            }
        });
        //作为守护线程运行，一旦主线程不在，这个线程自动结束
        _cleaner.setDaemon(true);
        _cleaner.start();
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
        if (_buckets.getFirst().containsKey(key)) {
            return true;
        } else {
            return false;
        }
    }

    public V get(K key) {
        return _buckets.getFirst().get(key);
    }

    public void put(K key, V value) {
        _buckets.getFirst().put(key, value);

    }

    public Object remove(K key) {
        return _buckets.getFirst().remove(key);
    }

    public int size() {
        return _buckets.getFirst().size();
    }

    //这个方法也太迷惑人了，作用就是把清理线程杀掉，这样数据就不会过期了，应该改名叫neverCleanup
    public void cleanup() {
        //中断清理线程中的sleep，_cleaner线程会抛出异常，然后_cleaner线程就死了，不再清理过期数据了
        _cleaner.interrupt();  //调用了interrupt后，再跑sleep就会抛InterruptedException异常

    }
}