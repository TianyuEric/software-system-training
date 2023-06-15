package com.projectpractice;

import com.alibaba.fastjson.JSON;
import com.projectpractice.entity.ProjectEntity;
import com.projectpractice.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @BelongsProject: projectPractice
 * @BelongsPackage: com.projectpractice
 * @Author: Tianyu Han
 * @CreateTime: 2023-06-05  19:27
 * @Description: projectTest
 * @Version: 1.0
 */
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectPracticeApplication.class)
@Slf4j
class ProjectApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp(){
        // 设定mockMvc能mock的controller是整个springBoot项目环境中的controller
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testQueryProjectReturnNull(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("yyyyyy");
        projectEntity.setId("1");
        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/queryProjectList")
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    // 正式执行接口,并返回接口的返回值
                    .andReturn();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void testQueryProject(){
        MvcResult mvcResult;
        ProjectEntity projectEntity2 = new ProjectEntity();
        projectEntity2.setProjectName(null);
        projectEntity2.setId(null);
        String body2 = JSON.toJSONString(projectEntity2);
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/queryProjectList")
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(body2).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    // 正式执行接口,并返回接口的返回值
                    .andReturn();
            log.info(mvcResult.getResponse().getContentAsString());
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddProject(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("hhhhh");
        projectEntity.setProjectContent("eeeee");
        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/addProjectInfo")
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    // 正式执行接口,并返回接口的返回值
                    .andReturn();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testModifyProject(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1");
        projectEntity.setProjectName("hhhhh");
        projectEntity.setProjectContent("55555");
        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/modifyProjectInfo")
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    // 正式执行接口,并返回接口的返回值
                    .andReturn();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testProjectDelete(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1");
        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/deleteProjectById")
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    // 正式执行接口,并返回接口的返回值
                    .andReturn();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testEntity(){
        String str = UserEntity.builder().id("").createdBy("")
                .creationDate(LocalDateTime.now()).startTime(LocalDateTime.now()).password("")
                .lastUpdatedBy("").status("").lastUpdateDate(LocalDateTime.now()).username("")
                .stopTime(LocalDateTime.now()).build().toString();
        String aaa = UserEntity.builder().toString();
        UserEntity userEntity = new UserEntity();
        UserEntity userEntity1 = new UserEntity();
        boolean equals = userEntity.equals(userEntity1);
        log.info(str + aaa + userEntity.hashCode());
        String str2 = ProjectEntity.builder().id("").creationDate(LocalDateTime.now()).createdBy("").projectName("").projectContent("")
                .userId("").lastUpdateDate(LocalDateTime.now()).lastUpdatedBy("").build().toString();
        String bbb = ProjectEntity.builder().toString();
        ProjectEntity projectEntity = new ProjectEntity();
        ProjectEntity projectEntity1 = new ProjectEntity();
        boolean equals1 = projectEntity.equals(projectEntity1);
        log.info(str2 + bbb + projectEntity.hashCode());
    }


}
