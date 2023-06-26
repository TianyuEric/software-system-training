package com.projectpractice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectpractice.entity.UserEntity;
import com.projectpractice.mapper.UserMapper;
import com.projectpractice.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceDefault
        extends ServiceImpl<UserMapper, UserEntity>
        implements UserService { }
