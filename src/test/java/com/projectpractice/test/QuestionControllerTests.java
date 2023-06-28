package com.projectpractice.test;

import com.alibaba.fastjson.JSON;
import com.projectpractice.ProjectPracticeApplication;
import com.projectpractice.dto.QuestionDto;
import com.projectpractice.entity.OptionEntity;
import com.projectpractice.entity.ProjectEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectPracticeApplication.class)
@Slf4j
public class QuestionControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp(){
        // 设定mockMvc能mock的controller是整个springBoot项目环境中的controller
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testUpdate(){
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId("1671556305706160129");
        questionDto.setName("aaa");
        questionDto.setIsMust("1");
        List<OptionEntity> list = new ArrayList<OptionEntity>();
        OptionEntity optionEntity = new OptionEntity();
        optionEntity.setQuestionId("1671556305706160129");
        optionEntity.setId("1672162102693777409");

        List<Map<String, String>> mapList = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        map.put("1671556305706160129","1672162102693777409");
        mapList.add(map);
        questionDto.setOption(list);

        String body = JSON.toJSONString(mapList);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/question/update")
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateWithEmpty(){
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId("1671556305706160120");
        questionDto.setName("aaa");
        questionDto.setIsMust("1");
        List<OptionEntity> list = new ArrayList<OptionEntity>();
        OptionEntity optionEntity = new OptionEntity();
        optionEntity.setQuestionId("1671556305706160129");
        optionEntity.setId("1672162102693777409");

        List<Map<String, String>> mapList = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        map.put("1671556305706160129","1672162102693777409");
        mapList.add(map);
        questionDto.setOption(list);

        String body = JSON.toJSONString(mapList);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/question/update")
                            .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                            .content(body).characterEncoding("utf-8")).andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAdd(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1672162078542974977");

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/question/add")
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
    void testAddWithEmpty(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1672162078542974973");

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/question/add")
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
    void testRemove(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1673151883548528641");

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/question/delete")
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
    void testRemoveWithEmpty(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1673151883548528645");

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/question/delete")
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
    void testLinkBank(){
        String body = JSON.toJSONString("");
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/question/bank")
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
    void testGetLinkQuestion(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1672933494305525761");

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/question/link")
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
    void testGetLinkQuestionEmpty(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1672933494305525761");

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/question/link")
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
    void testGetStatistic(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1672932350699151362");

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/question/statistic")
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
    void testGetStatisticEmpty(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1672932350699151362");

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/question/statistic")
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
    void testGetAll(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1673510366198755329");

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/question/all")
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
    void testGetAllEmpty(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("1673510366198755329");

        String body = JSON.toJSONString(projectEntity);
        MvcResult mvcResult;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/question/all")
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
}
