package fr.nvneuserback.userApi.services;

import fr.nvneuserback.userApi.entities.Entreprise;
public interface EntrepriseService {
	public Entreprise findByEmail(String email);
	public Entreprise registerEntreprise(Entreprise entreprise);
}
