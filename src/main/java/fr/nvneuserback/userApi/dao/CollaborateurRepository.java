package fr.nvneuserback.userApi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.nvneuserback.userApi.entities.Collaborateur;
@CrossOrigin("*")
@RepositoryRestResource
public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long> {
	public Collaborateur findByEmail(String email);
}
