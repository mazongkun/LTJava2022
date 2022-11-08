import java.util.*;

/**
 * 30. 串联所有单词的子串
 */
public class FindSubstring {
    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = new String[] {"foo", "bar"};
        FindSubstring findSubstring = new FindSubstring();
        findSubstring.findSubstring(s, words);
    }

    public List<Integer> findSubstring(String s, String[] words) {
        int wordsNum = words.length;
        int wordLen = words[0].length();
        int subStrLen = wordsNum * wordLen;

        int strLen = s.length();
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < strLen - subStrLen + 1; i++) {
            String tmpStr = s.substring(i, i + subStrLen);
            // 重置hashMap
            hashMap.clear();

            // 将tmpStr切成wordsNum段
            // 将每个子串放入hashMap，计数+1
            for (int j = 0; j < subStrLen; j += wordLen) {
                String curSub = tmpStr.substring(j, j + wordLen);
                hashMap.put(curSub, hashMap.getOrDefault(curSub, 0) + 1);
            }

            // 将每个word从hashMap，计数-1，若为0移除word
            for (int j = 0; j < wordsNum; j++) {
                String word = words[j];
                hashMap.put(word, hashMap.getOrDefault(word, 0) -1);
                if (hashMap.get(word) == 0) {
                    hashMap.remove(word);
                }
            }
            // 当最终hashMap为空，则表示子串与word一一对应
            if (hashMap.isEmpty()) {
                result.add(i);
            }
        }

        return result;
    }
}
