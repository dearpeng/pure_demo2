package com.alimama.server;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * redis缓存穿透测试
 * Created by PengWX on 2019/12/18.
 */
public class BloomFilterTest {
    private static int total = 1000000;
    /**
     * total:放入bloomfilter的数量
     * fpp:容错率,默认0.03
     */
    private static BloomFilter<Integer> bf = BloomFilter.create(Funnels.integerFunnel(), total,0.0003);
//    private static BloomFilter<Integer> bf = BloomFilter.create(Funnels.integerFunnel(), total, 0.001);

    public static void main(String[] args) {
        // 初始化1000000条数据到过滤器中
        for (int i = 0; i < total; i++) {
            bf.put(i);
        }

        // 匹配已在过滤器中的值，是否有匹配不上的
        for (int i = 0; i < total; i++) {
            if (!bf.mightContain(i)) {
                System.out.println("存在应该匹配没有匹配上的数据~~~");
            }
        }

        // 匹配不在过滤器中的10000个值，有多少匹配出来
        int count = 0;
        for (int i = total; i < total + 10000; i++) {
            if (bf.mightContain(i)) {
                count++;
            }
        }
        System.out.println("不应该匹配但是匹配上的个数：" + count);
    }
}
