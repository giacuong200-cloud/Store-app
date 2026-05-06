package store.app.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class AuthTest {

    // chạy trước mỗi test case
    @BeforeEach
    void setup() {
        Auth.clear();
    }

    // ================= NULL =================

    @Test
    public void testNullUsername() {
        assertFalse(Auth.login(null, "123"));
    }

    @Test
    public void testNullPassword() {
        assertFalse(Auth.login("NV001", null));
    }

    // ================= EMPTY =================

    @Test
    public void testEmptyUsername() {
        assertFalse(Auth.login("", "123"));
    }

    @Test
    public void testEmptyPassword() {
        assertFalse(Auth.login("NV001", ""));
    }

    // ================= WRONG =================

    @Test
    public void testWrongPassword() {
        assertFalse(Auth.login("NV001", "999"));
    }

    @Test
    public void testWrongUsername() {
        assertFalse(Auth.login("NV999", "123"));
    }

    // ================= CORRECT =================

    @Test
    public void testCorrectLogin() {
        assertTrue(Auth.login("NV001", "123"));
    }

    // ================= STATUS =================

    @Test
    public void testIsLogin() {
        Auth.login("NV001", "123");
        assertTrue(Auth.isLogin());
    }

    @Test
    public void testLogout() {
        Auth.login("NV001", "123");
        Auth.logout();
        assertFalse(Auth.isLogin());
    }

    @Test
    public void testCurrentUser() {
        Auth.login("NV001", "123");
        assertEquals("NV001", Auth.getCurrentUser());
    }
}