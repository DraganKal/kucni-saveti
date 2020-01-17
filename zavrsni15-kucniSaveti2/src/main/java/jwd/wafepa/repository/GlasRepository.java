package jwd.wafepa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Glas;

@Repository
public interface GlasRepository extends JpaRepository<Glas, Long>{

}
