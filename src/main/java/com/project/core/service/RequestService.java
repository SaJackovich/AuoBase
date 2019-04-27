package com.project.core.service;

import com.project.db.entity.Request;
import com.project.db.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RequestService {

    Request add(Request request, User user);

    List<Request> getAll();

    Request getById(int id);

    Page<Request> getBySearchQuery(String searchQuery, Pageable pageable);

}
