package com.project.core.service.impl;

import com.project.db.entity.User;
import com.project.db.repository.RequestRepository;
import com.project.core.service.RequestService;
import com.project.db.entity.Request;
import com.project.db.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RequestSpecification requestSpecification;

    @Override
    public Request add(Request request, User user) {
        request.setUser(user);
        return requestRepository.save(request);
    }

    @Override
    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    @Override
    public Request getById(int id) {
        return requestRepository.findOne(id);
    }

    @Override
    public Page<Request> getBySearchQuery(String searchQuery, Pageable pageable) {
        return requestRepository.findAll(requestSpecification.findBySearchQuery(searchQuery), pageable);
    }
}
