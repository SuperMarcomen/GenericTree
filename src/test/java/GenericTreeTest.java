import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GenericTreeTest {

    private static final GenericTree<Integer> GENERIC_TREE = new GenericTree<>(5);

    @BeforeAll
    static void setup() {
        GENERIC_TREE.add(4, 5);
        GENERIC_TREE.add(3, 5);
        GENERIC_TREE.add(2, 3);
        GENERIC_TREE.add(1, 2);
        GENERIC_TREE.add(0, 2);
        GENERIC_TREE.add(null, 4);
    }

    @Test
    void chineseStuff() {
        GenericTree<String> treeOperations = new GenericTree<>("你好");
        treeOperations.add("很高兴认识你", "你好");
        assertThrows(NoSuchElementException.class, () -> treeOperations.removeSubtree("你号"));

        assertEquals(List.of("你好", "很高兴认识你"), treeOperations.removeSubtree("你好"));
        assertThrows(NoSuchElementException.class, () -> treeOperations.removeSubtree("很高兴认识你"));
        assertThrows(NoSuchElementException.class, () -> treeOperations.getRoot());

        assertEquals(0, treeOperations.size());
        assertEquals(-1, treeOperations.height());


        GenericTree<String> treeOperations2 = new GenericTree<>("你好");
        treeOperations2.add("你会中文吗?", "你好");
        treeOperations2.add("你好，我会一点点中文", "你会中文吗?");
        treeOperations2.add("Sorry mate, I don't speak chinese", "你会中文吗?");
        treeOperations2.add("太好了， 你的大学很好", "你好，我会一点点中文");
        List<String> expected = List.of("你会中文吗?", "你好，我会一点点中文", "Sorry mate, I don't speak chinese", "太好了， 你的大学很好");
        assertEquals(expected, treeOperations2.removeSubtree("你会中文吗?"));
    }

    @Test
    @Order(1)
    void getRoot() {
        assertEquals(5, GENERIC_TREE.getRoot());
    }

    @Test
    @Order(2)
    void getChildren() {
        List<Integer> children = List.of(4, 3);
        assertEquals(children, GENERIC_TREE.getChildren(5));

        children = List.of(2);
        assertEquals(children, GENERIC_TREE.getChildren(3));

        assertEquals(Collections.emptyList(), GENERIC_TREE.getChildren(null));
    }

    @Test
    @Order(3)
    void getParent() {
        assertEquals(5, GENERIC_TREE.getParent(4));
        assertEquals(3, GENERIC_TREE.getParent(2));
    }

    @Test
    @Order(4)
    void add() {
    }

    @Test
    @Order(5)
    void size() {
        assertEquals(7, GENERIC_TREE.size());
    }

    @Test
    @Order(6)
    void removeLeaf() {
        GENERIC_TREE.removeLeaf(null);
        assertEquals(6, GENERIC_TREE.size());
    }

    @Test
    @Order(7)
    void height() {
        assertEquals(4, GENERIC_TREE.height());
    }

    @Test
    @Order(8)
    void removeSubtree() {
        List<Integer> nodes = List.of(3, 2, 1, 0);
        assertEquals(nodes, GENERIC_TREE.removeSubtree(3));
    }
}