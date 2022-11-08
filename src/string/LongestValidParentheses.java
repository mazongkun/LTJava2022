package string;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 32. 最长有效括号
 */
public class LongestValidParentheses {
    public static void main(String[] args) {
        String str = "(()";

        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        int result = longestValidParentheses.longestValidParentheses2(str);
        System.out.println(result);
    }

    /**
     * 栈方法
     * 始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」，这样的做法主要是考虑了边界条件的处理，栈里其他元素维护左括号的下标：
     *
     * 对于遇到的每个 ‘(’ ，我们将它的下标放入栈中
     * 对于遇到的每个 ‘)’ ，我们先弹出栈顶元素表示匹配了当前右括号：
     *      1.如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
     *      2.如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        char[] arr = s.toCharArray();
        int len = s.length();
        Deque<Integer> stack = new LinkedList<>();
        // -1 先入栈
        stack.push(-1);
        int maxPairLen = 0;

        for (int i = 0; i < len; i++) {
            // 左括号入栈
            if (arr[i] == '(') {
                stack.push(i);
            }

            // 右括号
            if (arr[i] == ')') {
                // 先出栈
                stack.pop();
                if (stack.isEmpty()) {
                    // 1、栈为空，下标入栈
                    stack.push(i);
                } else {
                    // 2、栈中有值，则计算 i - 栈顶的差值
                    maxPairLen = Math.max(maxPairLen, i - stack.peek());
                }
            }
        }
        return maxPairLen;
    }

    public int longestValidParentheses2(String s) {
        char[] arr = s.toCharArray();
        int len = s.length();
        // 记录左右括号的个数
        int left  = 0;
        int right = 0;
        int leftIndex  = 0;
        int rightIndex = 0;
        int maxLen = 0;

        // 先从左往右遍历
        for (int i = 0; i < len; i++) {
            if (arr[i] == '(') {
                if (left == 0 && right == 0) {
                    leftIndex = i;
                }
                left++;
            }
            if (arr[i] == ')') {
                rightIndex = i;
                right++;
            }

            if (left == right) {
                maxLen = Math.max(maxLen, rightIndex - leftIndex + 1);
            } else if (left < right) {
                // ')'多于'('，left与right归0
                left = right = 0;
            }
        }

        // 再从右往左遍历，以覆盖所有情况
        left = right = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (arr[i] == ')') {
                if (left == 0 && right == 0) {
                    rightIndex = i;
                }
                right++;
            }
            if (arr[i] == '(') {
                leftIndex = i;
                left++;
            }

            if (left == right) {
                maxLen = Math.max(maxLen, rightIndex - leftIndex + 1);
            } else if (left > right) {
                // '('多于')'，left与right归0
                left = right = 0;
            }
        }
        return maxLen;
    }
}
