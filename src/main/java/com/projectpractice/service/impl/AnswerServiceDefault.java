package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.dto.AnswerDto;
import com.projectpractice.entity.AnswerInfo;
import com.projectpractice.mapper.AnswerMapper;
import com.projectpractice.service.AnswerService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 答案服务实现类
 */
@Service
public class AnswerServiceDefault extends ServiceImpl<AnswerMapper, AnswerInfo> implements AnswerService {

    /**
     * 获取答案信息列表
     *
     * @param projectId 项目ID
     * @param username  用户名
     * @param pageSize  每页大小
     * @param pageNum   页码
     * @return 答案信息列表
     */
    @Override
    public List<AnswerDto> getAnswerInfoList(String projectId, String username, int pageSize, int pageNum) {
        return this.baseMapper.getAnswerInfo(projectId, username, pageSize, (pageNum - 1) * pageSize);
    }
}
