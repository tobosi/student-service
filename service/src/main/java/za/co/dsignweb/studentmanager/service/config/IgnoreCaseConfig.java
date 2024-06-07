package za.co.dsignweb.studentmanager.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ConfigurationProperties(prefix = "word-config")
@Configuration
public class IgnoreCaseConfig {
    private Set<String> wordIgnoreCase = new HashSet<String>()  ;

    public Set<String> getWordIgnoreCase() {
        return Collections.unmodifiableSet(wordIgnoreCase);
    }

    public void setWordIgnoreCase(final Set<String> wordIgnoreCase) {
        this.wordIgnoreCase = wordIgnoreCase;
    }
}
