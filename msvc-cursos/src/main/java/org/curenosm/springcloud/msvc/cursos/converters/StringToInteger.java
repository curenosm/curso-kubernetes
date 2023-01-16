package org.curenosm.springcloud.msvc.cursos.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToInteger implements Converter<String, Integer> {

    @Override
    public Integer convert(String source) {
        return Integer.valueOf(source);
    }
}
