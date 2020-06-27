package example.pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class Composite {
}

@Data
@AllArgsConstructor
abstract class Entry{
    protected String name;
    public abstract void print();
}

class File extends Entry {

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
        System.out.println("目录:"+name);
        list.forEach(File::print);
    }
}
