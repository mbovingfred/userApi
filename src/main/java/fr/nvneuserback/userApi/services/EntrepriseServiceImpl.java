package fr.nvneuserback.userApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.nvneuserback.userApi.dao.EntrepriseRepository;
import fr.nvneuserback.userApi.entities.Entreprise;
@Service
public class EntrepriseServiceImpl implements EntrepriseService {

	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Override
	public Entreprise registerEntreprise(Entreprise entreprise) {
		return this.entrepriseRepository.save(entreprise);
	}

	@Override
	public Entreprise findByEmail(String email) {
		return entrepriseRepository.findByEmail(email);
	}
}
