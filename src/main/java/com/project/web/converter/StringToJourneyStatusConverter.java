package com.project.web.converter;

import com.project.db.constant.JourneyStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StringToJourneyStatusConverter implements Converter<String, JourneyStatus> {

    private static final String INVALID_JOURNEY_STATUS = "Invalid journey status: ";
    private static final String VALID_JOURNEY_STATUS = " . Should be: 'closed', 'opened', 'canceled', 'process'";

    @Override
    public JourneyStatus convert(String status) {
        List<String> journeyStatus = Arrays.stream(JourneyStatus.values())
                .map(JourneyStatus::getStatus)
                .collect(Collectors.toList());
        if (!journeyStatus.contains(status)) {
            throw new IllegalArgumentException(INVALID_JOURNEY_STATUS + status + VALID_JOURNEY_STATUS);
        }
        return JourneyStatus.valueOf(status.toUpperCase());
    }

}
