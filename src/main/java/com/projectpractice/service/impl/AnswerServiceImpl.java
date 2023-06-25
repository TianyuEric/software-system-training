package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.dto.AnswerInfoDto;
import com.projectpractice.entity.AnswerEntity;
import com.projectpractice.mapper.AnswerMapper;
import com.projectpractice.service.AnswerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.service.impl
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-12  21:10
 * @Description: AnswerServiceImpl
 * @Version: 1.0
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, AnswerEntity> implements AnswerService {
    @Override
    public List<AnswerInfoDto> getAnswerInfoList(String projectId ,String username, int pageSize, int pageNum) {
        return this.baseMapper.getAnswerInfo(projectId, username, pageSize, (pageNum - 1) * pageSize);
    }
}
