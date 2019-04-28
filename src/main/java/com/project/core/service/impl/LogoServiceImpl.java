package com.project.core.service.impl;

import com.project.core.service.LogoService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class LogoServiceImpl implements LogoService {

    private static final String DEFAULT_LOGO_PATH = "src\\main\\resources\\logo\\";
    private static final String LOGO_CANNOT_BE_SAVE = "Logo cannot be save";

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    public String save(String path, String username, MultipartFile file) {
        String extension = file.getContentType().split("/")[1];
        try (FileOutputStream stream = new FileOutputStream(
                DEFAULT_LOGO_PATH + path + "\\" + username + "." + extension)) {
            stream.write(file.getBytes());
        } catch (IOException e) {
            log.error(LOGO_CANNOT_BE_SAVE);
        }
        return username + "." + extension;
    }
}
