package fr.nvneuserback.userApi;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.nvneuserback.userApi.entities.Collaborateur;
import fr.nvneuserback.userApi.dao.CollaborateurRepository;

@SpringBootApplication
public class UserApiApplication implements CommandLineRunner{

	@Autowired
	private CollaborateurRepository collaborateurRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserApiApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
//		accountService.saveUser(new AppUser(null,"admin","admin",null));
//		accountService.saveUser(new AppUser(null,"user","1234",null));
//		accountService.saveRole(new AppRole(null, "ADMIN"));
//		accountService.saveRole(new AppRole(null, "USER"));
//		accountService.addRoleToUse("admin", "USER");
//		accountService.addRoleToUse("user", "USER");
//		
		Collaborateur col = new Collaborateur(null, "test", "testprénom", null, "contact@nvne.com", "/cv", "34625746", DateFormat.getDateTimeInstance().format(new Date()), false, null, null);
		collaborateurRepository.save(col);
	}

}
