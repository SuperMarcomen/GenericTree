import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A class that represents a node in a tree structure.
 * Each node has a content of type T, a parent of type T, and a list of children nodes.
 * The class provides methods for accessing and modifying the node and its subtree.
 * @param <T> the type of the content and parent of the node
 * @author xxxxx
 * @version 1.0
 */
public class Node<T> {

    // The content of the node
    private final T content;
    // The parent of the node
    private final T parent;
    // The list of children nodes
    private final LinkedList<Node<T>> children;

    /**
     * Constructs a new node with the given content and parent.
     * @param content the content of the node
     * @param parent the parent of the node
     */
    public Node(T content, T parent) {
        this.content = content;
        this.parent = parent;
        this.children = new LinkedList<>();
    }

    /**
     * Returns the content of the node.
     * @return the content of the node
     */
    public T getContent() {
        return content;
    }

    /**
     * Returns the parent of the node.
     * @return the parent of the node
     */
    public T getParent() {
        return parent;
    }

    /**
     * Returns the height of the node in the tree.
     * The height is defined as the number of edges from the node to the deepest leaf in its subtree.
     * @return the height of the node
     */
    public int getHeight() {
        int height = 1;
        int highestChild = 0;
        for (Node<T> child : children) {
            int childHeight = child.getHeight();
            if (childHeight > highestChild) highestChild = childHeight;
        }
        return height + highestChild;
    }

    /**
     * Checks if the given node is contained in the subtree rooted at this node.
     * @param element the node to check
     * @return true if the node is contained in the subtree, false otherwise
     */
    public boolean isContainedInSubTree(Node<T> element) {
        for (Node<T> child : children) {
            if (child.isContainedInSubTree(element)) return true;
        }
        return element.equals(this);
    }

    /**
     * Checks if any of the children nodes is equal to the given node.
     * @param element the node to check
     * @return true if any of the children nodes is equal to the node, false otherwise
     */
    public boolean doChildrenContain(Node<T> element) {
        return children.contains(element);
    }

    /**
     * Adds a new child node to this node.
     * @param element the child node to add
     */
    public void addChild(Node<T> element) {
        children.add(element);
    }

    /**
     * Checks if this node has any children nodes.
     * @return true if this node has any children nodes, false otherwise
     */
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    /**
     * Removes the first occurrence of the given child node from this node.
     * @param element the child node to remove
     */
    public void removeChild(Node<T> element) {
        children.removeFirstOccurrence(element);
    }

    /**
     * Returns a list of all the children nodes of this node.
     * @return a list of all the children nodes
     */
    public List<Node<T>> getChildren() {
        return new ArrayList<>(children);
    }
}