package com.project.core.service.impl;

import com.project.core.service.FileService;
import com.project.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    private static final String DEFAULT_LOGO_FOLDER = "src\\main\\resources\\logo\\";
    private static final String AUTO_LOGO_FOLDER = "auto\\";
    private static final String CANNOT_ADD_AUTO_LOGO = "Cannot add auto logo!";

    @Autowired
    private UserService userService;

    @Override
    public void addAutoLogo(MultipartFile file) {
        String extension = file.getContentType().split("/")[1];
        try (FileOutputStream stream = new FileOutputStream(
                DEFAULT_LOGO_FOLDER + AUTO_LOGO_FOLDER + userService.getCurrentUser().getUsername() + "." + extension)) {
            stream.write(file.getBytes());
        } catch (IOException e) {
            System.err.println(CANNOT_ADD_AUTO_LOGO);
        }
    }
}
