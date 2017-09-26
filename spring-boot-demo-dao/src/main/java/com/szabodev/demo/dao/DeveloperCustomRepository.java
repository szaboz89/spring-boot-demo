package com.szabodev.demo.dao;

import com.szabodev.demo.model.Developer;

import java.util.List;

public interface DeveloperCustomRepository {

    List<Developer> findByDeveloperCriteria(DeveloperFilter developerFilter);
}
