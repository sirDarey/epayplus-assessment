package com.epayplus.assessment.repo;

import com.epayplus.assessment.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository <Task, Long> {

    boolean existsByTitle(String title);
}
