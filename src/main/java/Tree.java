import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * An ordered data type representing a hierarchical tree structure with a set of connected nodes. Each node in the tree
 * can be connected to many children but must be connected to exactly one parent, except for the root node, which has no
 * parent. These constraints mean there are no cycles or loops and also that each child can be treated like the root
 * node of its own subtree. The tree is considered empty if it contains no elements.
 * @param <T> the type of elements in this tree.
 */
public interface Tree<T> {

    /**
     * Returns the root element of the tree.
     * @return the root element.
     * @throws NoSuchElementException if this tree is empty.
     */
    T getRoot();

    /**
     * Returns the direct children of the first occurrence of a specified element in the tree. The order of occurrence
     * corresponds to the order of addition to the tree for this specific element.
     * @param parent is the specific element for which the direct children are to be returned.
     * @return the children in the order they were added to the tree or an empty collection if there are none.
     * @throws NoSuchElementException if the parent element is not contained in the tree.
     */
    Collection<T> getChildren(T parent);

    /**
     * Returns the direct parent of the first occurrence of a specified element in the tree. The order of occurrence
     * corresponds to the order of addition to the tree for this specific element.
     * @param child is the specific element for which the direct parent is to be returned.
     * @return the parent element of the specified child.
     * @throws IllegalArgumentException if the child element is the root element.
     * @throws NoSuchElementException if the child element is not contained in the tree.
     */
    T getParent(T child);

    /**
     * Inserts the specified element as a direct child of the first occurrence of a specified parent element in this
     * tree. The order of occurrence corresponds to the order of addition to the tree for this specific element.
     * @param element is the element to be inserted
     * @param parent is the parent element under which the specified element is to be inserted.
     * @throws NoSuchElementException if the parent element is not contained in the tree.
     */
    void add(T element, T parent);

    /**
     * Removes the first occurrence of the specified leaf element from the tree. The order of occurrence corresponds to
     * the order of addition to the tree for this specific element.
     * @param leaf is the element to be removed.
     * @throws IllegalArgumentException if the leaf element has children.
     * @throws NoSuchElementException if the leaf element is not part of the tree.
     */
    void removeLeaf(T leaf);

    /**
     * Removes the first occurrence of the specified element from the tree. Also removed both the direct and indirect
     * children of the element. The order of occurrence corresponds to the order of addition to the tree for this
     * specific element.
     * @param parent is the element to be removed.
     * @return all removed elements, in the order they were added to the tree.
     * @throws NoSuchElementException if the element is not part of the tree.
     */
    Collection<T> removeSubtree(T parent);

    /**
     * Returns the number of elements in this tree. If this tree contains more than {@code Integer.MAX_VALUE} elements,
     * returns {@code Integer.MAX_VALUE}.
     * @return the number of elements in this tree.
     */
    int size();

    /**
     * Returns the height of the tree. The height of a tree is defined as the length (number of edges) of the longest
     * path from the root node to any leaf node in the tree. If the height is more than {@code Integer.MAX_VALUE}
     * elements, returns {@code Integer.MAX_VALUE}.
     * @return the height of this tree or -1 if empty.
     */
    int height();

}
