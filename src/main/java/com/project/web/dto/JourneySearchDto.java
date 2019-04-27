package com.project.web.dto;

import com.project.db.constant.JourneyStatus;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class JourneySearchDto {

    private JourneyStatus status;

    private String searchQuery;
}
