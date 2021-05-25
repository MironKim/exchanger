package com.example.exchanger.rest.clients.controller;

import com.example.exchanger.rest.clients.controller.ClientsControllerTest.TestConfiguration;
import com.example.exchanger.rest.clients.controller.dto.ClientCreationParameters;
import com.example.exchanger.rest.clients.service.ClientsService;
import com.example.exchanger.rest.clients.service.exception.ClientNotFoundException;
import com.example.exchanger.rest.clients.service.model.ClientModel;
import com.example.exchanger.rest.common.exception.ApiException;
import com.example.exchanger.rest.common.exception.ApiNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientsController.class)
@ContextConfiguration(classes = {ClientsController.class, TestConfiguration.class})
class ClientsControllerTest {

    private static final String BASE_URL = "/clients";
    private static final String ID_URL = BASE_URL + "/{clientId}";

    private static final long CLIENT_ID = 1;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClientsService service;
    @Autowired
    private ClientsDtoModelMapper mapper;

    @BeforeEach
    void resetMock() {
        reset(service);
    }

    @Test
    void create() throws Exception {
        ClientCreationParameters parameters = getClientCreationParameters();

        mockMvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parameters)))
                .andExpect(status().isOk());

        verify(service).create(mapper.map(parameters));
    }

    private ClientCreationParameters getClientCreationParameters() {
        ClientCreationParameters parameters = new ClientCreationParameters();
        parameters.setPassword(PASSWORD);
        parameters.setUsername(USERNAME);
        return parameters;
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete(ID_URL, CLIENT_ID))
                .andExpect(status().isOk());

        verify(service).deleteById(CLIENT_ID);
    }

    @ParameterizedTest
    @MethodSource("parametersDeleteByIdExceptions")
    void deleteByIdExceptions(Class<RuntimeException> toBeThrown, Class<ApiException> expected) throws Exception {
        doThrow(toBeThrown).when(service).deleteById(CLIENT_ID);

        assertThatThrownBy(() -> mockMvc.perform(delete(ID_URL, CLIENT_ID))).hasCauseInstanceOf(expected);
    }

    @SuppressWarnings("unused")
    static Object[] parametersDeleteByIdExceptions() {
        return new Object[][] {
                {ClientNotFoundException.class, ApiNotFoundException.class}
        };
    }

    @Test
    void findAll() throws Exception {
        List<ClientModel> clients = Collections.singletonList(createClient());
        when(service.findAll()).thenReturn(clients);

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(clients)));
    }

    private ClientModel createClient() {
        ClientModel clientModel = new ClientModel();
        clientModel.setUsername(USERNAME);
        clientModel.setId(CLIENT_ID);
        return clientModel;
    }

    @Configuration
    public static class TestConfiguration extends WebSecurityConfigurerAdapter {

        @Bean
        public ClientsDtoModelMapper mapper() {
            return Mappers.getMapper(ClientsDtoModelMapper.class);
        }

        @Bean
        public ClientsService service() {
            return Mockito.mock(ClientsService.class);
        }

        @Override
        public void configure(WebSecurity web) {
            web.ignoring().antMatchers("/**");
        }
    }
}
