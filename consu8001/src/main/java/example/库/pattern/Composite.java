package example.库.pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * bridge模式：类与属性一对一，composite：类与属性一对多，都是组合的模型
 * 树形结构：非叶子节点与叶子节点继承同一抽象，非叶子节点持有叶子节点的集合
 * 不同节点继承同一抽象：与装饰类相同，在实现方法中，调用属性的相同方法，就会像递归一样一直调用下去
 */
public class Composite {
    public static void main(String[] args) {
        File f1 = new File("ab.text");
        File f2 = new File("cd.text");
        File f3 = new File("ef.text");
        Directory d1 = new Directory("directory1");
        Directory d2 = new Directory("directory1");
    }
}

@AllArgsConstructor
@Data
abstract class Entry {
    protected String name;
    abstract public void print();
}

class File extends Entry{

    public File(String name) {
        super(name);
    }

    @Override
    public void print() {
        System.out.println("文件:"+name);
    }
}

class Directory extends Entry{

    private List<File> list = new ArrayList<>();

    public Directory(String name) {
        super(name);
    }

    @Override
    public void print() {
        System.out.println("-----目录:"+name);
        list.forEach(File::print);
    }
}
