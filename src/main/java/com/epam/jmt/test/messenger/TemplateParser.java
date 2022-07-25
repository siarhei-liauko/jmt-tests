package com.epam.jmt.test.messenger;

import java.util.Map;

public interface TemplateParser {

    String parse(String template, Map<String, String> values);
}
