package fr.nvneuserback.userApi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.nvneuserback.userApi.entities.Entreprise;

@CrossOrigin("*")
@RepositoryRestResource
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
	public Entreprise findByEmail(String email);
}
