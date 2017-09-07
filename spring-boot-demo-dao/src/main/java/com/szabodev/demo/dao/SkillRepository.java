package com.szabodev.demo.dao;

import com.szabodev.demo.model.Skill;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SkillRepository extends CrudRepository<Skill, Long> {
}
