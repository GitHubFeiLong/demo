package com.ssm.mf.utils;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
import org.apache.commons.io.Charsets;

/**
 * 布隆过滤器
 * @ClassName BloomFilterUtil
 * @Description TODO
 * @Author cfl
 * @Date 2019/6/24 14:22
 * @Version 1.0
 */
public class BloomFilterUtil {
	public static void main(String[] args) {
		// 新建bloomFilter
		BloomFilter<String> filter = BloomFilter.create(new Funnel<String>() {
			@Override
			public void funnel(String from, PrimitiveSink into) {
				into.putString(from, Charsets.UTF_8);
			}
		},10000, 0.003);
		
		// 添加数据
		for (int i = 0; i < 5000; i++) {
			filter.put("abc_test_" + i);
		}
		
		System.out.println("write all...");
		
		int count = 0;
		// 3. 测试结果
		for (int i = 5000; i < 10000; i++) {
			if (filter.mightContain("abc_test_" + i)) {
				count ++;
			} else{
			}
		}
		System.out.println("bloomFilter判断正确有：" + count + "次");
	}
}
