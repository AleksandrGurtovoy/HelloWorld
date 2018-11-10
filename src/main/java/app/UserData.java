package app;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Locale;


@ToString
public class UserData {
    private static Logger LOG = LoggerFactory.getLogger(UserData.class);

    private @Getter
    @Setter
    int hours;
    private @Getter
    @Setter
    Locale locale;

    public UserData() {
        this.locale = Locale.getDefault();
        this.hours = LocalDateTime.now().getHour();
        LOG.info("Created app.UserData instance with locale:" + locale
                + " and local time: " + hours + "!");
    }

    public boolean validateData() {
        LOG.trace("Start validating user data");

        if (locale == null) {
            LOG.trace("The locale can not be null");
            return false;
        }
        if (hours > 23 || hours < 0) {
            LOG.trace("The customer hours must be in range from 0 to 23 hours");
            return false;
        }
        LOG.trace("Validating is success");
        return true;
    }


}
