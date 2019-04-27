package com.project.core.service.impl;

import com.project.core.service.LogoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class LogoServiceImpl implements LogoService {

    private static final String DEFAULT_LOGO_PATH = "src\\main\\resources\\logo\\";
    private static final String LOGO_CANNOT_BE_SAVE = "Logo cannot be save";

    @Override
    public String save(String path, String username, MultipartFile file) {
        String extension = file.getContentType().split("/")[1];
        try (FileOutputStream stream = new FileOutputStream(
                DEFAULT_LOGO_PATH + path + "\\" + username + "." + extension)) {
            stream.write(file.getBytes());
        } catch (IOException e) {
            System.out.println(LOGO_CANNOT_BE_SAVE);
        }
        return username + "." + extension;
    }
}
