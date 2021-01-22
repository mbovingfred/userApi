/**
 * 
 */
package fr.nvneuserback.userApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.nvneuserback.userApi.dao.CollaborateurRepository;
import fr.nvneuserback.userApi.entities.Collaborateur;

/**
 * @author MBOVING
 *
 */
@Service
public class CollaborateurServiceImpl implements CollaborateurService {

	@Autowired
	private CollaborateurRepository collaborateurRepository;
	
	@Override
	public Collaborateur registerCollaborateur(Collaborateur collaborateur) {
		return this.collaborateurRepository.save(collaborateur);
	}

	@Override
	public Collaborateur findByEmail(String email) {
		return collaborateurRepository.findByEmail(email);
	}

}
