package com.cms.services;

import com.cms.model.Conference;
import com.cms.repositories.ConferencesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferencesService {
    private final ConferencesRepository conferencesRepository = new ConferencesRepository();

    /**
     * Returns one Conference with the given id as parameter.
     * @param id String unique id of Conference
     * @return a Conference
     */
    public Conference getById(String id) {
        return conferencesRepository.getById(id);
    }

    /**
     * Returns all the existing Conferences in the database.
     * @return List of Conference
     */
    public List<Conference> getConferences() {
        return conferencesRepository.getConferences();
    }
}
