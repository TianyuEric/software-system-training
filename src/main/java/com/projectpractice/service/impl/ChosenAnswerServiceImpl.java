package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.ChosenAnswer;
import com.projectpractice.mapper.ChosenAnswerMapper;
import com.projectpractice.service.ChosenAnswerService;
import org.springframework.stereotype.Service;


@Service
public class ChosenAnswerServiceImpl extends ServiceImpl<ChosenAnswerMapper, ChosenAnswer> implements ChosenAnswerService {
}
