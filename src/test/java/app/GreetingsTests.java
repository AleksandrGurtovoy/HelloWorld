package app;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

public class GreetingsTests {
    @Test
    public void test_type() throws Exception {
        assertNotNull(Greetings.class);
    }

    private static final Greetings greeting = new Greetings();
    private UserData userData;

    @Test
    public void test_instantiation() throws Exception {
        assertNotNull(greeting);
    }

    @Before
    public void set_up() {
        userData = new UserData();
    }

    @Test
    public void test_getMessage_morning_us() throws Exception {
        userData.setLocale(Locale.US);
        userData.setHours(8);
        assertEquals("Good morning, World!", greeting.getMessage(userData));
    }

    @Test
    public void test_getMessage_night_us() throws Exception {
        userData.setLocale(Locale.US);
        userData.setHours(23);
        assertEquals("Good night, World!", greeting.getMessage(userData));
    }

    private static final Locale dLocale = new Locale("ru", "RU");
    ;

    @Test
    public void test_getMessage_day_ru() throws Exception {
        userData.setLocale(dLocale);
        userData.setHours(10);
        assertEquals("Добрый день, Мир!", greeting.getMessage(userData));
    }

    @Test
    public void test_getMessage_evening_ru() throws Exception {
        userData.setLocale(dLocale);
        userData.setHours(19);
        assertEquals("Добрый вечер, Мир!", greeting.getMessage(userData));
    }

    @Test
    public void test_getMessage_fallback() throws Exception {
        userData.setLocale(Locale.CHINA);
        assertEquals("Good day, World!", greeting.getMessage(userData));
    }

    @Test
    public void test_validationLocale() throws Exception {
        userData.setLocale(dLocale);
        assertTrue(greeting.validationLocale(userData));
        userData.setLocale(Locale.US);
        assertTrue(greeting.validationLocale(userData));
    }

    @Test
    public void test_validationLocale_error() throws Exception {
        userData.setLocale(Locale.GERMAN);
        assertFalse(greeting.validationLocale(userData));
    }

    @Test(expected = NullPointerException.class)
    public void test_getMessage_error_locale() throws Exception {
        userData.setLocale(null);
        userData.setHours(19);
        assertEquals("Добрый вечер, Мир!", greeting.getMessage(userData));

    }
}
