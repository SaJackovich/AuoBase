package com.project.web.advice.editor;

import com.project.core.service.RequestService;
import com.project.db.entity.Request;

import java.beans.PropertyEditorSupport;

public class RequestPropertyEditor extends PropertyEditorSupport {

    private RequestService requestService;

    public RequestPropertyEditor(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public void setAsText(String id) { setValue(getRequestById(id)); }

    private Request getRequestById(String id) {
        return requestService.getById(Integer.parseInt(id));
    }
}
