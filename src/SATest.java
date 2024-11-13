import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SATest {
    private SA sa;

    @BeforeEach
    void setUp() {
        sa = new SA();
    }

    @Test
    void addCategory_shouldAddValidCategory() {
        sa.addCategory("Electronics");
        assertTrue(sa.getCategories().contains("electronics"));
    }

    @Test
    void addCategory_shouldIgnoreDuplicateCategory() {
        sa.addCategory("Books");
        sa.addCategory("books"); // Duplicate in different case
        assertEquals(1, sa.getCategories().size());
        assertTrue(sa.getCategories().contains("books"));
    }

    @Test
    void addCategory_shouldNotAddNullCategory() {
        sa.addCategory(null);
        assertTrue(sa.getCategories().isEmpty());
    }

    @Test
    void addCategory_shouldNotAddEmptyCategory() {
        sa.addCategory("   ");
        assertTrue(sa.getCategories().isEmpty());
    }

    @Test
    void addCategory_shouldAddTrimmedCategory() {
        sa.addCategory("   Clothing   ");
        assertTrue(sa.getCategories().contains("clothing"));
    }

    @Test
    void removeCategory_shouldRemoveExistingCategory() {
        sa.addCategory("Furniture");
        sa.removeCategory("furniture");
        assertFalse(sa.getCategories().contains("furniture"));
    }

    @Test
    void removeCategory_shouldNotRemoveNonExistentCategory() {
        sa.addCategory("Toys");
        sa.removeCategory("appliances");
        assertEquals(1, sa.getCategories().size());
        assertTrue(sa.getCategories().contains("toys"));
    }

    @Test
    void removeCategory_shouldNotRemoveWithNullCategory() {
        sa.addCategory("Books");
        sa.removeCategory(null);
        assertEquals(1, sa.getCategories().size());
    }

    @Test
    void removeCategory_shouldNotRemoveWithEmptyCategory() {
        sa.addCategory("Movies");
        sa.removeCategory("   ");
        assertEquals(1, sa.getCategories().size());
    }

    @Test
    void removeCategory_shouldRemoveTrimmedCategory() {
        sa.addCategory("Gardening");
        sa.removeCategory("   gardening   ");
        assertFalse(sa.getCategories().contains("gardening"));
    }
}
