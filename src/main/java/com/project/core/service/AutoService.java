package com.project.core.service;

import com.project.web.dto.AutoSearchDto;
import com.project.db.entity.Auto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AutoService {

    List<Auto> getAll();

    Page<Auto> getFittingAutoByRequest(AutoSearchDto searchDto, Pageable pageable);

    void add(Auto auto);

    Auto getById(int id);

    void delete(Auto auto);
}
