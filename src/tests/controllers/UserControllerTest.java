package tests.controllers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import Controllers.UserController;
import Models.Admin;
import Models.User;

public class UserControllerTest {
    private UserController userController;
    private static final String TEST_USERNAME = "testUser";
    private static final String TEST_PASSWORD = "testPass123";
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    @BeforeEach
    void setUp() {
        // Get fresh instance for each test
        userController = UserController.getInstance();
        // Ensure we're logged out
        userController.logout();
    }

    @Nested
    @DisplayName("Registration Tests")
    class RegistrationTests {
        @Test
        @DisplayName("Should successfully register new user")
        void registerNewUser() {
            assertTrue(userController.registerUser(TEST_USERNAME, TEST_PASSWORD));
        }

        @Test
        @DisplayName("Should fail to register duplicate username")
        void registerDuplicateUser() {
            userController.registerUser(TEST_USERNAME, TEST_PASSWORD);
            assertFalse(userController.registerUser(TEST_USERNAME, "differentPassword"));
        }
    }

    @Nested
    @DisplayName("Authentication Tests")
    class AuthenticationTests {
        @BeforeEach
        void setupUser() {
            userController.registerUser(TEST_USERNAME, TEST_PASSWORD);
        }

        @Test
        @DisplayName("Should successfully login with correct credentials")
        void loginSuccess() {
            assertTrue(userController.login(TEST_USERNAME, TEST_PASSWORD));
            assertNotNull(userController.getCurrentUser());
            assertEquals(TEST_USERNAME, userController.getCurrentUser().getUsername());
        }

        @Test
        @DisplayName("Should fail login with incorrect password")
        void loginWrongPassword() {
            assertFalse(userController.login(TEST_USERNAME, "wrongPassword"));
            assertNull(userController.getCurrentUser());
        }

        @Test
        @DisplayName("Should fail login with non-existent username")
        void loginNonExistentUser() {
            assertFalse(userController.login("nonExistentUser", TEST_PASSWORD));
            assertNull(userController.getCurrentUser());
        }

        @Test
        @DisplayName("Should successfully logout")
        void logoutTest() {
            userController.login(TEST_USERNAME, TEST_PASSWORD);
            assertNotNull(userController.getCurrentUser());
            
            userController.logout();
            assertNull(userController.getCurrentUser());
        }
    }

    @Nested
    @DisplayName("Admin Permission Tests")
    class AdminPermissionTests {
        private final String REGULAR_USER = "regularUser";
        private final String REGULAR_PASS = "regularPass";

        @BeforeEach
        void setupUsers() {
            userController.registerUser(REGULAR_USER, REGULAR_PASS);
        }

        @Test
        @DisplayName("Admin should be able to toggle sell permission")
        void adminToggleSellPermission() {
            // Login as admin
            assertTrue(userController.login(ADMIN_USERNAME, ADMIN_PASSWORD));
            assertTrue(userController.isAdmin());

            // Toggle permission
            assertTrue(userController.toggleUserSellPermission(REGULAR_USER));
        }

        @Test
        @DisplayName("Regular user should not be able to toggle permissions")
        void regularUserCannotTogglePermissions() {
            // Login as regular user
            assertTrue(userController.login(REGULAR_USER, REGULAR_PASS));
            assertFalse(userController.isAdmin());

            // Attempt to toggle permission
            assertFalse(userController.toggleUserSellPermission(REGULAR_USER));
            assertFalse(userController.toggleUserBidPermission(REGULAR_USER));
        }

        @Test
        @DisplayName("Cannot toggle permissions for non-existent user")
        void cannotTogglePermissionsNonExistentUser() {
            // Login as admin
            assertTrue(userController.login(ADMIN_USERNAME, ADMIN_PASSWORD));

            // Attempt to toggle permission for non-existent user
            assertFalse(userController.toggleUserSellPermission("nonExistentUser"));
            assertFalse(userController.toggleUserBidPermission("nonExistentUser"));
        }

        @Test
        @DisplayName("Cannot toggle admin permissions")
        void cannotToggleAdminPermissions() {
            // Login as admin
            assertTrue(userController.login(ADMIN_USERNAME, ADMIN_PASSWORD));

            // Attempt to toggle admin's permissions
            assertFalse(userController.toggleUserSellPermission(ADMIN_USERNAME));
            assertFalse(userController.toggleUserBidPermission(ADMIN_USERNAME));
        }
    }

    @Nested
    @DisplayName("Singleton Tests")
    class SingletonTests {
        @Test
        @DisplayName("Should always return the same instance")
        void singletonInstance() {
            UserController instance1 = UserController.getInstance();
            UserController instance2 = UserController.getInstance();
            
            assertSame(instance1, instance2);
        }
    }
}