package ClinicaOdontologica.repository;

import ClinicaOdontologica.entity.Odontologo;
import ClinicaOdontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OdontologoRepository extends JpaRepository<Odontologo,Long> {


}
