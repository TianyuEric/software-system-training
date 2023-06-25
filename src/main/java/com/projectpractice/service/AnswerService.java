package com.projectpractice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projectpractice.dto.AnswerInfoDto;
import com.projectpractice.entity.AnswerEntity;

import java.util.List;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.service
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-12  21:07
 * @Description: AnswerService
 * @Version: 1.0
 */
public interface AnswerService extends IService<AnswerEntity> {

    List<AnswerInfoDto> getAnswerInfoList(String projectId,String username, int pageSize, int pageNum);
}
