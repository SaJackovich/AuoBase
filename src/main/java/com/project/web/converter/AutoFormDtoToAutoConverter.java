package com.project.web.converter;

import com.project.core.exception.NullFormDtoException;
import com.project.db.entity.Auto;
import com.project.web.dto.AutoFormDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AutoFormDtoToAutoConverter implements Converter<AutoFormDto, Auto> {

    private static final String AUTO_CONDITION_PERFECT = "perfect";
    private static final String AUTO_FORM_IS_NULL = "Auto form is null";

    @Override
    public Auto convert(AutoFormDto autoFormDto) {
        if (Objects.isNull(autoFormDto)) {
            throw new NullFormDtoException(AUTO_FORM_IS_NULL);
        }
        Auto auto = new Auto();
        auto.setModel(autoFormDto.getModel());
        auto.setCarrying(autoFormDto.getCarrying());
        auto.setHeight(autoFormDto.getHeight());
        auto.setWidth(autoFormDto.getWidth());
        auto.setCondition(autoFormDto.getCondition().equals(AUTO_CONDITION_PERFECT));
        return auto;
    }
}
