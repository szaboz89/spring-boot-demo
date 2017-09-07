package com.szabodev.demo.dao;

import com.szabodev.demo.model.Developer;
import org.springframework.data.repository.CrudRepository;

public interface DeveloperRepository extends CrudRepository<Developer, Long> {
}
