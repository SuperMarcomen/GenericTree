import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A generic tree class
 * @param <T> the type of the tree
 * @author xxx
 * @version 1.0
 */
public class GenericTree<T> implements Tree<T> {

    public static final String EMPTY_TREE = "The tree is empty!";
    public static final String ELEMENT_NOT_IN_TREE = "This element is not contained in the tree!";
    public static final String NOT_A_LEAF = "This node is not a leaf since it has children!";
    private final LinkedList<Node<T>> nodes;

    public GenericTree(T root) {
        this.nodes = new LinkedList<>();
        Node<T> node = new Node<>(root, null);
        nodes.add(node);
    }

    @Override
    public T getRoot() {
        if (nodes.isEmpty()) {
            throw new NoSuchElementException(EMPTY_TREE);
        }
        return nodes.get(0).getContent();
    }

    @Override
    public Collection<T> getChildren(T parent) {
        Node<T> parentNode = getNode(parent);
        if (parentNode == null) {
            throw new NoSuchElementException(ELEMENT_NOT_IN_TREE);
        }

        List<T> children = new ArrayList<>();
        for (Node<T> child : parentNode.getChildren()) {
            children.add(child.getContent());
        }
        return children;
    }

    @Override
    public T getParent(T child) {
        for (Node<T> node : nodes) {
            if (!node.doChildrenContain(getNode(child))) continue;
            return node.getContent();
        }
        throw new NoSuchElementException(ELEMENT_NOT_IN_TREE);
    }

    @Override
    public void add(T element, T parent) {
        Node<T> parentNode = getNode(parent);
        if (parentNode == null) {
            throw new NoSuchElementException(ELEMENT_NOT_IN_TREE);
        }
        Node<T> elementNode = new Node<>(element, parent);
        nodes.add(elementNode);
        parentNode.addChild(elementNode);
    }

    @Override
    public void removeLeaf(T leaf) {
        Node<T> leafNode = getNode(leaf);
        Node<T> parentNode = getNode(getParent(leaf));

        if (leafNode == null || parentNode == null) {
            throw new NoSuchElementException(ELEMENT_NOT_IN_TREE);
        }
        if (leafNode.hasChildren()) {
            throw new IllegalArgumentException(NOT_A_LEAF);
        }

        parentNode.removeChild(leafNode);
        nodes.removeFirstOccurrence(leafNode);
    }

    @Override
    public Collection<T> removeSubtree(T parent) {
        Node<T> rootNode = getNode(parent);

        if (rootNode == null) {
            throw new NoSuchElementException(ELEMENT_NOT_IN_TREE);
        }

        List<T> subtreeElements = new ArrayList<>();
        for (Node<T> node : nodes) {
            if (!rootNode.isContainedInSubTree(node)) continue;
            subtreeElements.add(node.getContent());
        }

        for (T subtreeElement : subtreeElements) {
            nodes.removeFirstOccurrence(getNode(subtreeElement));
        }

        // in case the root node is the root of the whole tree, the parent is null
        Node<T> parentNode;
        try {
            parentNode = getNode(getParent(parent));
            if (parentNode != null) parentNode.removeChild(rootNode);
        } catch (NoSuchElementException ignored) {
        }

        nodes.removeFirstOccurrence(rootNode);
        return subtreeElements;
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public int height() {
        if (nodes.isEmpty()) return -1;
        return nodes.get(0).getHeight();
    }

    private Node<T> getNode(T element) {
        if (element == null) return null;
        for (Node<T> node : nodes) {
            if (!element.equals(node.getContent())) continue;
            return node;
        }
        return null;
    }
}
