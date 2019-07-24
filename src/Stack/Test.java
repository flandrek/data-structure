package Stack;

import java.util.Random;

/**
 * ��������ջ������
 * ArrayStack
 * LinkedListStack
 * �ĸ��Ӷ�����ͬ�ģ�����O(n)
 *
 * @author Administrator
 */
public class Test {
    public static void main(String[] args) {

        int opCount = 10000000;

        ArrayStack<Integer> arrayStack = new ArrayStack<Integer>();
        double timeArr = testStack(arrayStack, opCount);
        System.out.println("ArrayStack,time:" + timeArr + "s");

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<Integer>();
        double timeList = testStack(linkedListStack, opCount);
        System.out.println("LinkedListStack,time:" + timeList + "s");
    }

    //����ʹ��q����opCount��enqueue��dequeue��������Ҫ��ʱ��
    private static double testStack(Stack<Integer> s, int opCount) {

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            s.push(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            s.pop();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }
}
