package org.curenosm.springcloud.msvc.cursos.converters;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Set;

/**
 * Because GenericConverter is a more complex SPI interface, you should use it only
 * when you need it. Favor Converter or ConverterFactory for basic type conversion needs.
 */
public class GenericConverterImpl implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return null;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        return null;
    }

}
