package com.project.core.service.impl;

import com.project.core.service.AutoService;
import com.project.db.entity.Auto;
import com.project.db.repository.AutoRepository;
import com.project.db.specification.AutoSpecification;
import com.project.web.dto.AutoSearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoServiceImpl implements AutoService {

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private AutoSpecification autoSpecification;

    @Override
    public void add(Auto auto) {
        autoRepository.save(auto);
    }

    @Override
    public Auto getById(int id) {
        return autoRepository.findOne(id);
    }

    @Override
    public void delete(Auto auto) {
        autoRepository.delete(auto);
    }

    @Override
    public List<Auto> getAll() {
        return autoRepository.findAll();
    }

    @Override
    public Page<Auto> getFittingAutoByRequest(AutoSearchDto dto, Pageable pageable) {
        return autoRepository.findAll(autoSpecification.findByCondition(dto), pageable);
    }
}
