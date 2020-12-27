package fr.nvneuserback.userApi.services;

import fr.nvneuserback.userApi.entities.Collaborateur;

public interface CollaborateurService {
	public Collaborateur registerCollaborateur(Collaborateur collaborateur);
	public Collaborateur findByEmail(String email);
}
