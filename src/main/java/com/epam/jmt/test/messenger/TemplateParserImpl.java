package com.epam.jmt.test.messenger;

import com.epam.jmt.test.exception.ParameterNotFoundException;
import com.epam.jmt.test.model.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TemplateParserImpl implements TemplateParser {

    @Override
    public String parse(String template, Map<String, String> values) {
        String result = template;
        List<Parameter> parameters = getParameters(template);
        for (Parameter parameter : parameters) {
            var value = values.get(parameter.getKey());
            if (value == null) {
                throw new ParameterNotFoundException(String.format("Parameter with name %s was not found in specified values", parameter.getKey()));
            }
            result = result.replace(template.substring(parameter.getStartAt(), parameter.getEndAt()), value);
        }
        return result;
    }

    private List<Parameter> getParameters(String template) {
        List<Parameter> parameters = new ArrayList<>();
        int i = 0;
        while (i < template.length()) {
            if (template.charAt(i) == '#' && template.charAt(i+1) == '{') {
                int endIndex = template.indexOf('}', i);
                if (endIndex == -1) {
                    break;
                }
                endIndex++;
                String expression = template.substring(i, endIndex);
                int internalExpressionIndex = expression.indexOf('#', i+2);
                if (
                        internalExpressionIndex != -1
                                && expression.indexOf('{', internalExpressionIndex + 1) != -1
                ) {
                    i += 2;
                    continue;
                }
                Parameter parameter = new Parameter(expression.replaceAll("[#{}]", ""), i, endIndex);
                parameters.add(parameter);
                i = endIndex + 1;
            } else {
                i++;
            }
        }

        return parameters;
    }
}
