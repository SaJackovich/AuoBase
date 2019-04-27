package com.project.web.advice.editor;

import com.project.core.service.AutoService;
import com.project.db.entity.Auto;

import java.beans.PropertyEditorSupport;

public class AutoPropertyEditor extends PropertyEditorSupport {

    private AutoService autoService;

    public AutoPropertyEditor(AutoService autoService) { this.autoService = autoService; }

    @Override
    public void setAsText(String id) { setValue(getAutoById(id)); }

    private Auto getAutoById(String id) {
        return autoService.getById(Integer.parseInt(id));
    }
}
