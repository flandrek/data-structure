package Set;
/**
 * ����һ�������б�ÿ�����ʿ���д��ÿ����ĸ��ӦĦ��˹�������ϡ�
 * ���磬"cab" ����д�� "-.-..--..."��(�� "-.-." + "-..." + ".-"�ַ����Ľ��)��
 * ���ǽ�����һ�����ӹ��̳������ʷ��롣�������ǿ��Ի�����дʲ�ͬ���ʷ����������
 *
 * @author wangzhe
 */

import java.util.TreeSet;

public class Solution804 {
    public int uniqueMorseRepresentations(String[] words) {
        //26��Ӣ����ĸ��Ӧ��Ħ˹����
        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
                "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--."
                , "--.-", ".-.", "...", "-", "..-", "...-",
                ".--", "-..-", "-.--", "--.."};
        TreeSet<String> set = new TreeSet<>();
        for (String word : words) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < word.length(); i++)
                //�ҵ���ĸ��Ӧ��Ħ˹��(��ȥ��ĸ��ʼ��ƫ��)
                res.append(codes[word.charAt(i) - 'a']);
            //Ħ˹����ͬʱset�Զ�����
            set.add(res.toString());
        }
        return set.size();
    }
}
