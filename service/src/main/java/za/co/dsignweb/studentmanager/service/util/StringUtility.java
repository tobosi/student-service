package za.co.dsignweb.studentmanager.service.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Component;
import za.co.dsignweb.studentmanager.service.config.IgnoreCaseConfig;

import java.util.Set;

@Component
public class StringUtility {

    private static Set<String> words;

    public StringUtility(final IgnoreCaseConfig ignoreCaseConfig) {
        words = ignoreCaseConfig.getWordIgnoreCase();
    }

    public String capitalize(final String name) {
        if (name == null) {
            return null;
        }
        return StringUtils.capitalize(trim(name).toLowerCase()  );
    }

    public String capitalizeWord(final String name) {
        final StringBuilder sb = new StringBuilder();
        final String[] wordArray = name.trim().split("\\s+");

        int index = 0;
        if (wordArray.length > 0) {
            for(final String word : wordArray) {
                if (!words.contains(word)) {
                    sb.append(capitalize(word));
                } else {
                    sb.append(trim(word));
                }

                if ((wordArray.length - 1 != index)) {
                    sb.append(" ");
                }
                index++;
            }
            return sb.toString();
        } else {
            return WordUtils.capitalize(trim(name));
        }
    }


    public String trim(final String name) {
        return StringUtils.trim(name);
    }
}
