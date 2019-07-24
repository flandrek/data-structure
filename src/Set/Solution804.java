package Set;
/**
 * 给定一个单词列表，每个单词可以写成每个字母对应摩尔斯密码的组合。
 * 例如，"cab" 可以写成 "-.-..--..."，(即 "-.-." + "-..." + ".-"字符串的结合)。
 * 我们将这样一个连接过程称作单词翻译。返回我们可以获得所有词不同单词翻译的数量。
 *
 * @author wangzhe
 */

import java.util.TreeSet;

public class Solution804 {
    public int uniqueMorseRepresentations(String[] words) {
        //26个英文字母对应的摩斯密码
        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
                "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--."
                , "--.-", ".-.", "...", "-", "..-", "...-",
                ".--", "-..-", "-.--", "--.."};
        TreeSet<String> set = new TreeSet<>();
        for (String word : words) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < word.length(); i++)
                //找到字母对应的摩斯码(减去字母初始的偏移)
                res.append(codes[word.charAt(i) - 'a']);
            //摩斯码相同时set自动忽略
            set.add(res.toString());
        }
        return set.size();
    }
}
