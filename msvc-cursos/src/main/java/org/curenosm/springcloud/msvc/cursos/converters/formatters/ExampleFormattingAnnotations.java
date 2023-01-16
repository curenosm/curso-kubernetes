package org.curenosm.springcloud.msvc.cursos.converters.formatters;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ExampleFormattingAnnotations {

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal decimal;

    /**
     * By default, date and time fields not annotated with @DateTimeFormat
     * are converted from strings by using the DateFormat.SHORT
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;
}
