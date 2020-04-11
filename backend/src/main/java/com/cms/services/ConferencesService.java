package com.cms.services;

import com.cms.model.Conference;
import com.cms.repositories.ConferencesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferencesService {
    private final ConferencesRepository conferencesRepository = new ConferencesRepository();

    public Conference getById(String id) {
        return conferencesRepository.getById(id);
    }
}
