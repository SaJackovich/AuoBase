package com.project.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutoSearchDto {

    private double height;
    private double width;
    private int carrying;
    private String modelSearch;

}
