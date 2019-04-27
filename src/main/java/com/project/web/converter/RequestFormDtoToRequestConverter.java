package com.project.web.converter;

import com.project.core.exception.NullFormDtoException;
import com.project.db.entity.Request;
import com.project.web.dto.RequestFormDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RequestFormDtoToRequestConverter implements Converter<RequestFormDto, Request> {

    private static final String REQUEST_FORM_IS_NULL = "Request form is null";

    @Override
    public Request convert(RequestFormDto formDto) {
        if (Objects.isNull(formDto)) {
            throw new NullFormDtoException(REQUEST_FORM_IS_NULL);
        }
        return Request.builder()
                .height(formDto.getHeight())
                .width(formDto.getWidth())
                .carrying(formDto.getCarrying())
                .message(formDto.getMessage())
                .from(formDto.getFrom())
                .to(formDto.getTo())
                .build();
    }

}
