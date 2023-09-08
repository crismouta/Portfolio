package com.randstad.portfolio.infra.repositories;

import com.randstad.portfolio.domain.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectRepository extends JpaRepository<Project, Long> {
}
