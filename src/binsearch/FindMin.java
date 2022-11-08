package binsearch;

/**
 * 154. 寻找旋转排序数组中的最小值 II
  */

public class FindMin {
    public static void main(String[] args) {


        int[] arr = {2,2,2,0,1};

        FindMin findMin = new FindMin();
        int result = findMin.findMin(arr);
        System.out.println(result);
    }


    public int findMin(int[] nums) {
        int len = nums.length;
        int start = 0;
        int end = len - 1;

        while (start < end) {
            int middle = start + ((end - start) / 2);

            if (nums[middle] < nums[end]) {
                end = middle;
            } else if (nums[middle] > nums[end]) {
                start = middle + 1;
            } else {
                end --;
            }
        }

        return nums[start];
    }
}
