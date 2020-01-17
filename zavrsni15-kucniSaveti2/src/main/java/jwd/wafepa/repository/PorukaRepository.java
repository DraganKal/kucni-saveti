package jwd.wafepa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Poruka;
@Repository
public interface PorukaRepository extends JpaRepository<Poruka, Long>{
	
	Page<Poruka> findByZgradaId(Long id, Pageable page);
	
	@Query("SELECT p FROM Poruka p WHERE "
			+ "(:zgradaId IS NULL OR p.zgrada.id = :zgradaId) AND "
			+ "(:naslov IS NULL OR p.naslov like :naslov) AND "
			+ "(:tip IS NULL OR p.tip like :tip) "
			)
	Page<Poruka> search(
			@Param("zgradaId") Long zgradaId, 
			@Param("naslov") String naslov, 
			@Param("tip") String tip, 
			Pageable pageRequest);

}
