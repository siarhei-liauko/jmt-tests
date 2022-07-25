package com.epam.jmt.test.messanger;

import com.epam.jmt.test.exception.ParameterNotFoundException;
import com.epam.jmt.test.messenger.TemplateParserImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TemplateParserImplTest {

    private static final String TEMPLATE = "Hello #{userName}, welcome to #{serviceName}!";

    @InjectMocks
    private TemplateParserImpl templateParser;

    @Test
    public void testParseValidTemplate() {
        Map<String, String> params = Map.of(
                "userName", "Player 1",
                "serviceName", "oursupersite.com"
        );
        String expectedResult = "Hello Player 1, welcome to oursupersite.com";

        String actualResult = templateParser.parse(TEMPLATE, params);

        assertEquals(expectedResult, actualResult, "Actual message should be the same as expected message");
    }

    @Test
    public void testParseTemplateWithExtraParams() {
        Map<String, String> params = Map.of(
                "userName", "Player 1",
                "serviceName", "oursupersite.com",
                "extra", "extra param"
        );
        String expectedResult = "Hello Player 1, welcome to oursupersite.com";

        String actualResult = templateParser.parse(TEMPLATE, params);

        assertEquals(expectedResult, actualResult, "Extra param must be ignored and actual message should be the same as expected message");
    }

    @Test
    public void testParseTemplateWithMissingParam() {
        Map<String, String> params = Map.of(
                "userName", "Player 1"
        );

        assertThrows(ParameterNotFoundException.class,
                () -> templateParser.parse(TEMPLATE, params),
                "ParameterNotFoundException must be thrown if value for at least one placeholder is not provided");
    }

    @Test
    public void testParseTemplateWithSpecificParam() {
        Map<String, String> params = Map.of(
                "userName", "Player 1",
                "serviceName", "#{our-service}"
        );
        String expectedResult = "Hello Player 1, welcome to #{our-service}";

        String actualResult = templateParser.parse(TEMPLATE, params);

        assertEquals(expectedResult, actualResult, "Actual message should be the same as expected message");
    }

    @Test
    public void testParseMistypedTemplate() {
        String template = "Hello #{userName, welcome to #{service}!";
        Map<String, String> params = Map.of(
                "userName", "Player 1",
                "serviceName", "oursupersite.com"
        );
        String expectedResult = "Hello #{userName, welcome to #{service}!";

        String actualResult = templateParser.parse(template, params);

        assertEquals(expectedResult, actualResult, "Actual message should be the same as expected message");
    }
}
