package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.Option;
import com.projectpractice.mapper.OptionMapper;
import com.projectpractice.service.OptionService;
import org.springframework.stereotype.Service;

@Service
public class OptionServiceImpl extends ServiceImpl<OptionMapper, Option> implements OptionService {
}
