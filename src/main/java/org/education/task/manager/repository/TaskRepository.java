package org.education.task.manager.repository;

import org.education.task.manager.model.domain.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Integer> {

    Optional<TaskEntity> findById(Integer Id);

    Collection<TaskEntity> findAll();

    void deleteById(Integer id);

    TaskEntity save(TaskEntity entity);


}
