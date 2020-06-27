package example.库.队列;

public class CircleQueue {

    private Integer[] elements;

    private int head=0;

    private int size=0;

    public void offer(Integer data) {
        elements[(head + size++) % elements.length] = data;
    }

    public Integer poll(){
        Integer element = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return element;
    }
}
