package org.education.task.manager.model.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "topic")
    private String topic;
    @Column(name = "definition")
    private String definition;
    @Column(name = "task_link")
    private String taskLink;


}
