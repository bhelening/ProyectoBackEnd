package com.example.ClinicaOdontologica;

import ClinicaOdontologica.ClinicaOdontologicaApplication;
import ClinicaOdontologica.dto.OdontologoDTO;
import ClinicaOdontologica.dto.PacienteDTO;
import ClinicaOdontologica.entity.Odontologo;
import ClinicaOdontologica.entity.Paciente;
import ClinicaOdontologica.entity.Turno;
import ClinicaOdontologica.exception.BadRequestException;
import ClinicaOdontologica.service.OdontologoService;
import ClinicaOdontologica.service.PacienteService;
import ClinicaOdontologica.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = ClinicaOdontologicaApplication.class)
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TurnoService turnoService;
    public void cargarDatos() throws BadRequestException {
        PacienteDTO paciente = new PacienteDTO(
                "Helen",
                "Bernal",
                "1111",
                LocalDate.of(2024, 6, 20),
                "Calle",
                123,
                "City",
                "Ecuador",
                "helen.b@mail.com");

        Paciente pacienteRespuesta = pacienteService.pacienteDTOToEntity(pacienteService.guardarPaciente(paciente));
        Odontologo odontologo = odontologoService.odontologoDTOToEntity(odontologoService.guardarOdontologo(new OdontologoDTO(12345, "Karina", "Aviles")));
        turnoService.nuevoTurno(new Turno(pacienteRespuesta, odontologo, LocalDate.of(2024, 06, 19)));
    }
    @Test
    public void listarTodosLosTurnos() throws Exception {
        cargarDatos();
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/turno").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}
