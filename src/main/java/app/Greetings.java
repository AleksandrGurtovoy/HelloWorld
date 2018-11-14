package app;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Greetings {
    /*Logger*/
    private static Logger LOG = LoggerFactory.getLogger(Greetings.class);

    /* Supported languages and default locale, if the customer locale not supported*/
    private static final List<String> supportedLanguages = Arrays.asList("ru", "en");
    private static final Locale defaultLocale = Locale.ENGLISH;


    public static void main(String[] args) {
        UserData userData = new UserData();
        Greetings greetings = new Greetings();
        String message = greetings.getMessage(userData);
        System.out.println(message);
    }

    public String getMessage(UserData userData) {
        if (!validationLocale(userData)) {
            userData.setLocale(defaultLocale);
        }
        String messageName = getMessageName(userData);
        ResourceBundle bundle = ResourceBundle.getBundle("message", userData.getLocale());
        return bundle.getString(messageName);
    }

    public String getMessageName(UserData userData) {
        String message = "";
        if (userData.getHours() > 6 && userData.getHours() < 9) {
            LOG.info("Loading message for morning greeting!");
            message = "morning";
        } else if (userData.getHours() > 8 && userData.getHours() < 19) {
            LOG.info("Loading message for day greeting!");
            message = "day";
        } else if (userData.getHours() > 18 && userData.getHours() < 23) {
            LOG.info("Loading message for evening greeting!");
            message = "evening";
        } else {
            LOG.info("Loading message for night greeting!");
            message = "night";
        }
        return message;
    }

    public boolean validationLocale(UserData userData) {
        LOG.info("Validating locale is started..");
        File dir = new File("src/main/resources");
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);
        for (File file : lst) {
            if (file.getName().contains("_" + userData.getLocale().getLanguage())) {
                return true;
            }
        }
        return false;
    }
}
