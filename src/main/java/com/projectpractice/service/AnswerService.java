package com.projectpractice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.projectpractice.dto.AnswerDto;
import com.projectpractice.entity.AnswerInfo;

import java.util.List;


public interface AnswerService extends IService<AnswerInfo> {
    List<AnswerDto> getAnswerInfoList(String projectId, String username, int pageSize, int pageNum);
}
