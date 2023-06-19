import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GenericTreeTest {

    private static GenericTree<Integer> genericTree = new GenericTree<>(5);

    @BeforeAll
    static void setup() {
        genericTree.add(4, 5);
        genericTree.add(3, 5);
        genericTree.add(2, 3);
        genericTree.add(1, 2);
        genericTree.add(0, 2);
        genericTree.add(null, 4);
    }

    @Test
    @Order(1)
    void getRoot() {
        assertEquals(5, genericTree.getRoot());
    }

    @Test
    @Order(2)
    void getChildren() {
        List<Integer> children = List.of(4, 3);
        assertEquals(children, genericTree.getChildren(5));

        children = List.of(2);
        assertEquals(children, genericTree.getChildren(3));
    }

    @Test
    @Order(3)
    void getParent() {
        assertEquals(5, genericTree.getParent(4));
        assertEquals(3, genericTree.getParent(2));
    }

    @Test
    @Order(4)
    void add() {
    }

    @Test
    @Order(5)
    void removeLeaf() {
    }

    @Test
    @Order(6)
    void size() {
        assertEquals(7, genericTree.size());
    }

    @Test
    @Order(7)
    void height() {
        assertEquals(4, genericTree.height());
    }

    @Test
    @Order(8)
    void removeSubtree() {
        List<Integer> nodes = List.of(3, 2, 1, 0);
        assertEquals(nodes, genericTree.removeSubtree(3));
    }
}