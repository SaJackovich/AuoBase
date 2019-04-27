package com.project.core.service.impl;

import com.project.core.exception.BDOperationException;
import com.project.core.service.JourneyService;
import com.project.db.constant.JourneyStatus;
import com.project.db.entity.Auto;
import com.project.db.entity.Journey;
import com.project.db.entity.Request;
import com.project.db.entity.User;
import com.project.db.repository.JourneyRepository;
import com.project.db.specification.JourneySpecification;
import com.project.web.dto.JourneySearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class JourneyServiceImpl implements JourneyService {

    private static final String JOURNEY_WAS_NOT_ADDED = "Journey wasn't added to db";

    private JourneyRepository journeyRepository;

    private JourneySpecification journeySpecification;

    private Map<JourneyStatus, JourneyStatusChanger> map;

    @Autowired
    public JourneyServiceImpl(JourneyRepository journeyRepository, JourneySpecification journeySpecification) {
        this.journeyRepository = journeyRepository;
        this.journeySpecification = journeySpecification;
        init();
    }

    @Override
    public List<Journey> getAll() {
        return journeyRepository.findAll();
    }

    @Override
    public Journey getById(long id) {
        return journeyRepository.findOne(id);
    }

    @Override
    public Page<Journey> getBySearchParams(JourneySearchDto searchDto, Pageable pageable) {
        return journeyRepository.findAll(journeySpecification.findByCondition(searchDto), pageable);
    }

    @Override
    public Journey add(Request request, Auto auto) {
        Journey journey = journeyRepository.save(createJourneyByRequest(request));
        journey.setAuto(auto);
        journey.setUser(request.getUser());
        Journey addedJourney = journeyRepository.save(journey);
        if (Objects.nonNull(addedJourney)) {
            auto.setProcess(true);
            return addedJourney;
        }
        throw new BDOperationException(JOURNEY_WAS_NOT_ADDED);
    }

    private Journey createJourneyByRequest(Request request) {
        return Journey.builder()
                .from(request.getFrom())
                .to(request.getTo())
                .status(JourneyStatus.OPENED)
                .build();
    }

    @Override
    public void changeJourneyStatus(Journey journey, User user, JourneyStatus status, Boolean autoCondition) {
        map.get(status)
                .apply(journey, autoCondition);
        journey.setStatus(status);
        journeyRepository.save(journey);
    }

    @Override
    public Page<Journey> getBySearchParamsAndDriver(JourneySearchDto searchDto, User user, Pageable pageable) {
        return journeyRepository.findAll(journeySpecification.findByConditionAndDriver(searchDto, user), pageable);
    }

    @FunctionalInterface
    public interface JourneyStatusChanger {

        void apply(Journey journey, Boolean autoCondition);

    }

    private void setJourneyStart(Journey journey, Boolean autoCondition) {
        journey.setStart(new Date());
    }

    private void setJourneyEnd(Journey journey, Boolean autoCondition) {
        journey.setEnd(new Date());
        journey.setUser(null);
        freeAutoFromJourney(journey, autoCondition);
    }

    private void setJourneyCanceled(Journey journey, Boolean autoCondition) {
        journey.setUser(null);
        freeAutoFromJourney(journey, autoCondition);
    }

    private void freeAutoFromJourney(Journey journey, Boolean autoCondition) {
        Auto journeyAuto = journey.getAuto();
        journeyAuto.setProcess(false);
        journeyAuto.setCondition(autoCondition);
        journey.setAuto(null);
    }

    private void init() {
        map = new HashMap<>();
        map.put(JourneyStatus.PROCESS, this::setJourneyStart);
        map.put(JourneyStatus.CLOSED, this::setJourneyEnd);
        map.put(JourneyStatus.CANCELED, this::setJourneyCanceled);
    }

}
