package com.project.web.advice.editor;

import com.project.core.service.JourneyService;
import com.project.db.entity.Journey;

import java.beans.PropertyEditorSupport;

public class JourneyPropertyEditor extends PropertyEditorSupport {

    private JourneyService journeyService;

    public JourneyPropertyEditor(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @Override
    public void setAsText(String id) {
        setValue(convertTextToJourney(id));
    }

    private Journey convertTextToJourney(String id) {
        return journeyService.getById(Long.valueOf(id));
    }
}
