package com.project.web.controller.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class EditorUriQuery {

    private static final String PAGE = "page";

    public static String getCurrentUri() {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .toUriString();
    }

    public static String getUriWithPage(int page) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replaceQueryParam(PAGE, page - 1)
                .toUriString();
    }

    public static String getUriWithParam(String param, String value) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replaceQueryParam(param, value)
                .toUriString();
    }

}
