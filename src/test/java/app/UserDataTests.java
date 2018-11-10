package app;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserDataTests {
    @Test
    public void test_type() throws Exception {
        assertNotNull(UserData.class);
    }

    @Test
    public void test_instantiation() throws Exception {
        UserData data = new UserData();
        assertNotNull(data);
    }

    @Test
    public void test_validateData_true() throws Exception {
        UserData data = new UserData();
        assertTrue(data.validateData());
    }

    @Test
    public void test_validateData_error() throws Exception {
        UserData data = new UserData();
        data.setHours(24);
        assertFalse(data.validateData());
    }
}
