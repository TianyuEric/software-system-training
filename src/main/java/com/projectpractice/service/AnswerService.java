package com.projectpractice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projectpractice.dto.AnswerInfoDto;
import com.projectpractice.entity.AnswerEntity;

import java.util.List;


public interface AnswerService extends IService<AnswerEntity> {

    List<AnswerInfoDto> getAnswerInfoList(String projectId,String username, int pageSize, int pageNum);
}
