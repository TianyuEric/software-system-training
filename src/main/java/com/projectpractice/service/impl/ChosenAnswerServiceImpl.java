package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.ChosenAnswerEntity;
import com.projectpractice.mapper.ChosenAnswerMapper;
import com.projectpractice.service.ChosenAnswerService;
import org.springframework.stereotype.Service;


@Service
public class ChosenAnswerServiceImpl extends ServiceImpl<ChosenAnswerMapper, ChosenAnswerEntity> implements ChosenAnswerService {
}
