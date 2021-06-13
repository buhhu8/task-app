package org.education.task.manager.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TaskDto {

    private Integer id;
    private String topic;
    private String definition;
    private String taskLink;

}
