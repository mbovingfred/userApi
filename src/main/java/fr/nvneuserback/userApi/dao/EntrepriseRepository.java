package fr.nvneuserback.userApi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.nvneuserback.userApi.entities.Entreprise;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

@CrossOrigin("*")
@RepositoryRestResource
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
	public Entreprise findByEmail(String email);
//        @RestResource(path = "/byNom")
//        public List<Entreprise> FindByNomContains(@Param("n") String nom);
//        @RestResource(path = "/byNomPage")
//        public Page<Entreprise> FindByNomContains(@Param("n") String nom, Pageable pageable);
        
}