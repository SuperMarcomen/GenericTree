import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Node<T> {

    private final T content;
    private final T parent;
    private final LinkedList<Node<T>> children;

    public Node(T content, T parent) {
        this.content = content;
        this.parent = parent;
        this.children = new LinkedList<>();
    }

    public T getContent() {
        return content;
    }

    public T getParent() {
        return parent;
    }

    public boolean isContainedInSubTree(Node<T> element) {
        for (Node<T> child : children) {
            if (child.isContainedInSubTree(child)) return true;
        }
        return element.equals(this);
    }

    public boolean doChildrenContain(Node<T> element) {
        return children.contains(element);
    }

    public void addChild(Node<T> element) {
        children.add(element);
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public void removeChild(Node<T> element) {
        children.removeFirstOccurrence(element);
    }

    public List<Node<T>> getChildren() {
        return new ArrayList<>(children);
    }
}
