import java.util.Arrays;

/**
 * 164. 最大间距
 */
public class MaximumGap {
    public static void main(String[] args) {
        int[] arr = new int[] {1,1,1,1};

        MaximumGap gap = new MaximumGap();
        int result = gap.maximumGap2(arr);
        System.out.println(result);
    }
    // 先排序，再遍历计算相邻数字的差值
    public int maximumGap(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
//         快排较慢，耗时与内存都胜5%
//        Utils.qSort(nums, 0, len - 1);
        // 基数排序
        Utils.radixSort(nums);

        int maxGap = Integer.MIN_VALUE;
        for (int i = 0; i < len - 1; i++) {
            int gap = nums[i + 1] - nums[i];
            maxGap = Math.max(gap, maxGap);
        }

        return maxGap;
    }

    // 桶排序，根据桶的位置定位边界，再比较有值的桶，比较大桶的左边界与小桶的有边界的差值
    public int maximumGap2(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }

        int maxNum = Arrays.stream(nums).max().getAsInt();
        int minNum = Arrays.stream(nums).min().getAsInt();

        int d = Math.max (1, (maxNum - minNum) / (len - 1)); // 桶的边界范围，至少为1（int向下取整，所以下面计算桶大小时要+1）
        int bucketSize = (maxNum - minNum) / d + 1; // 根据桶的边界范围，计算桶的个数
        int[][] bucket = new int[bucketSize][2];

        // 所有桶的左右边界初始化为-1，表示空桶
        for (int i = 0; i < bucketSize; i++) {
            Arrays.fill(bucket[i], -1);
        }

        // 遍历数组，装桶
        for (int num: nums) {
            int index = (num - minNum) / d;
            if (bucket[index][0] == -1) {
                // 桶为空时，左右边界都设为num
                bucket[index][0] = bucket[index][1] = num;
            } else {
                // 桶总已有值时
                bucket[index][0] = Math.min(bucket[index][0], num);
                bucket[index][1] = Math.max(bucket[index][1], num);
            }
        }

        // 遍历桶，找到最大差值
        int maxGap = 0;
        int smallRight = -1;
        for (int i = 0; i < bucketSize; i++) {
            if (bucket[i][0] == -1) {
                // 桶为空
                continue;
            }

            // 桶中有值
            // 小桶的右边界赋值
            if (smallRight == -1) {
                smallRight = bucket[i][1];
            } else {
                // 小桶右边界已赋值，直接比较当前桶左边界和小桶右边界
                int bigLeft = bucket[i][0];
                maxGap = Math.max(maxGap, bigLeft - smallRight);

                // 更新小桶右边界
                smallRight = bucket[i][1];
            }
        }
        return maxGap;
    }
}



















