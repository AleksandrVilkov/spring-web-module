package ru.edu.springweb.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.edu.springweb.config.WebApplicationConfig;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebApplicationConfig.class)
public class BookControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getBooksTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getBookTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void saveBookTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .content("{\"id\": 4," +
                                "\"title\": \"Тестовая книга\"," +
                                "\"author\": \"Тестовый автор\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void updateBookTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/books/2")
                        .content("{\"id\": 2," +
                                "\"title\": \"Тестовая книга\"," +
                                "\"author\": \"Тестовый автор\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void deleteBookTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}