package UnionFind;

public interface UF {

    int getSize();

    boolean isConnected(int p, int q); //判断两个元素之间是否连接

    void unionElements(int p, int q); //将两个元素合并到一起,变成一个集合中的元素
}
