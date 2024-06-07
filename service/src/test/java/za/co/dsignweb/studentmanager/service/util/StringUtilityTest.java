package za.co.dsignweb.studentmanager.service.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.dsignweb.studentmanager.service.config.IgnoreCaseConfig;

import java.util.Set;

@SpringBootTest(classes = { IgnoreCaseConfig.class  })
class StringUtilityTest {
    private StringUtility stringUtility;

    @BeforeEach
    public void setUp() {
        final IgnoreCaseConfig ignoreCaseConfig = new IgnoreCaseConfig();
        final Set<String> words = Set.of("at", "above");
        ignoreCaseConfig.setWordIgnoreCase(words);

        stringUtility = new StringUtility(ignoreCaseConfig);
    }

    @Test
    public void testTitleCaseForLowerCaseString() {
        final String data = stringUtility.capitalize("testing");
        Assertions.assertEquals("Testing", data);
    }

    @Test
    public void testTitleCaseForUppercaseString() {
        final String data = stringUtility.capitalize("TESTING");
        Assertions.assertEquals("Testing", data);
    }

    @Test
    public void testTitleCaseForSplitWords() {
        final String data = stringUtility.capitalizeWord("TESTING this");
        Assertions.assertEquals("Testing This", data);
    }

    @Test
    public void testTitleCaseForSplitWords1() {
        final String data = stringUtility.capitalizeWord("TESTING at TESTinG");
        Assertions.assertEquals("Testing at Testing", data);
    }
}
