package org.education.task.manager.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@RequiredArgsConstructor
public class TaskDto {

    private Integer id;
    private String topic;
    private String definition;
    private String taskLink;
    private MultipartFile file;

}
