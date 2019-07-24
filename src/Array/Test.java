package Array;

public class Test {
    public static void main(String[] args) {
        Array<Integer> arr = new Array<Integer>(20);
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);
    }
}
