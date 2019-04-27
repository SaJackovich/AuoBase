package com.project.core.service;

import com.project.db.constant.JourneyStatus;
import com.project.db.entity.Auto;
import com.project.db.entity.Journey;
import com.project.db.entity.Request;
import com.project.db.entity.User;
import com.project.web.dto.JourneySearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JourneyService {

    List<Journey> getAll();

    Journey getById(long id);

    Page<Journey> getBySearchParams(JourneySearchDto searchDto, Pageable pageable);

    Journey add(Request request, Auto auto);

    void changeJourneyStatus(Journey journey, User user, JourneyStatus status, Boolean autoCondition);

    Page<Journey> getBySearchParamsAndDriver(JourneySearchDto parameterDto, User user, Pageable pageable);
}
