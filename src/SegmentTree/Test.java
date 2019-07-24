package SegmentTree;

public interface Test {
    public static void main(String[] args) {

        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segTree = new SegmentTree<Integer>(nums,
                new Merger<Integer>() {
                    @Override
                    public Integer merge(Integer a, Integer b) {
                        return a + b;
                    }
                });
        System.out.println(segTree);

        System.out.println(segTree.query(0, 2));
        System.out.println(segTree.query(1, 3));
        System.out.println(segTree.query(2, 4));
        System.out.println(segTree.query(2, 5));
    }
}
