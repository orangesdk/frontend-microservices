package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureRestDocs(outputDir="target/snippets")
@AutoConfigureMockMvc
public class ResourceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void resource() throws Exception {
        mockMvc.perform(get("/resource")).andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("Hello World")))
                .andDo(document("resource"));
    }

    @Test
    public void app() throws Exception {
        mockMvc.perform(get("/js/app.js")).andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("angular")))
                .andDo(document("app"));
    }

    @Test
    public void template() throws Exception {
        mockMvc.perform(get("/templates/message.html")).andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("{{home.message.id}}")))
                .andDo(document("template"));
    }
}
