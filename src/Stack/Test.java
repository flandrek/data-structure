package Stack;

import java.util.Random;

/**
 * 测试两种栈的性能
 * ArrayStack
 * LinkedListStack
 * 的复杂度是相同的，都是O(n)
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

    //测试使用q运行opCount个enqueue和dequeue操作所需要的时间
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
