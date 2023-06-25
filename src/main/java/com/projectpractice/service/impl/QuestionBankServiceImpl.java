package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.QuestionBankEntity;
import com.projectpractice.mapper.QuestionBankMapper;
import com.projectpractice.service.QuestionBankService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice.service.impl
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-23  22:02
 * @Description: QuestionBankServiceImpl
 * @Version: 1.0
 */
@Service
public class QuestionBankServiceImpl extends ServiceImpl<QuestionBankMapper, QuestionBankEntity> implements QuestionBankService {
}
