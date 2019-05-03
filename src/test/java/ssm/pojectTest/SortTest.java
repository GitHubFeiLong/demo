package ssm.pojectTest;

import java.util.Arrays;

/**
 * 十以内的排序
 * @author msi
 * @date 2019年5月2日
 */
public class SortTest {
	public static void main(String [] args) {
		int [] arr = new int[11];
		int [] arr1 = {2,3,2,4,5,3,6,7,8,9};
		for (int i = 0; i < arr1.length; i++) {
			arr[arr1[i]] += 1;
		}
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(arr1));
		
		for (int i = arr.length - 1; i > 0; i--) {
			if (arr[i] > 0) {
				for (int j = arr[i]; j > 0; j--) {
					System.out.print(i + ",");
				}
			}
		}
	}

}
