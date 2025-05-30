package br.senac.sp.guiarestaurante.repository;

import br.senac.sp.guiarestaurante.model.Restaurante;
import org.springframework.data.repository.CrudRepository;

public interface RestauranteRepository extends CrudRepository<Restaurante, Long> { }
