package org.example.converter;
import org.springframework.core.convert.converter.Converter;
import java.math.BigDecimal;

public class DoubleToBigDecimalConverter implements Converter<Double, BigDecimal> {

    @Override
    public BigDecimal convert(Double source) {
        if (source == null) {
            return null;
        }
        return new BigDecimal(source.toString());
    }
}