package com.project.web.converter;

import com.project.core.exception.NullFormDtoException;
import com.project.db.entity.Request;
import com.project.web.dto.AutoSearchDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RequestToAutoSearchDtoConverter implements Converter<Request, AutoSearchDto> {

    private static final String CREATING_JOURNEY_IS_NULL = "Request for creating journey is null";

    @Override
    public AutoSearchDto convert(Request request) {
        if (Objects.isNull(request)) {
            throw new NullFormDtoException(CREATING_JOURNEY_IS_NULL);
        }
        return AutoSearchDto.builder()
                .width(request.getWidth())
                .height(request.getHeight())
                .carrying(request.getCarrying())
                .build();
    }
}
