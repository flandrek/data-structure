package Queue;

import java.util.Random;

/**
 * 测试几种队列的性能
 * ArrayQueue的出队为O(n)
 * LoopQueue的出队为O(1)
 * LinkedListQueue的出队为O(1)
 *
 * @author Administrator
 */
public class Test {
    public static void main(String[] args) {

        int opCount = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>();
        double timeArr = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue,time:" + timeArr + "s");

        LoopQueue<Integer> loopQueue = new LoopQueue<Integer>();
        double timeLoop = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue,time:" + timeLoop + "s");

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<Integer>();
        double timeLinked = testQueue(linkedListQueue, opCount);
        System.out.println("LinkedListQueue,time:" + timeLinked + "s");
    }

    //测试使用q运行opCount个enqueue和dequeue操作所需要的时间
    private static double testQueue(Queue<Integer> q, int opCount) {

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            q.dequeue();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }
}
