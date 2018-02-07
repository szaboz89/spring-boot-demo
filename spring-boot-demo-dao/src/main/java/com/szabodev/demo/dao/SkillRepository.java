package com.szabodev.demo.dao;

import com.szabodev.demo.model.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<Skill, Long> {
}
