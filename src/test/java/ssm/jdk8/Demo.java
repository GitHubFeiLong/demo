/**
 * 
 */
package ssm.jdk8;

import java.util.Arrays;

/**   
 * @ClassName:  Demo   
 * @Description:TODO   
 * @author: cfl
 * @date:  2019年6月20日 下午1:51:03     
 */
public class Demo {
	public static void main(String[] args) {
		int [] arr = {1,3,4,5,2,4,10};
		int max = 0;	// 数组中最大值
		int min = 0;	// 数组中最小值
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > max) {
				max = arr[i];
			}
			if (arr[i] < min) {
				min = arr[i];
			}
		}
		
		System.out.println("min:" + min);
		System.out.println("max:" + max);
		
		int[] arr1 = new int[max - min + 1];
		for(int i = 0; i < arr.length; i++) {
			arr1[arr[i]] ++;
		}
		System.out.println(Arrays.toString(arr1));
	}

}
