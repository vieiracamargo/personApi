package github.com.crudapi.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
class PersonContollerTest {


    @Autowired
    private  MockMvc mvc;


    @Test
    @DisplayName("Devolve código http 400 quando recebe informações inválidas")
    void createPerson() throws Exception {
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/person")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Devolve código http 404 quando o regristro não existe no banco de dados.")
    void getPerson() throws Exception {
        var id = 3;
        var url = String.format("/api/v1/person/%d", id);
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(url)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }


}