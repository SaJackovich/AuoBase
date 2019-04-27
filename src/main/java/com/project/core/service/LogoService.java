package com.project.core.service;

import org.springframework.web.multipart.MultipartFile;

public interface LogoService {

    String save(String path, String username, MultipartFile file);

}
