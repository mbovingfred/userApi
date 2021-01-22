package fr.nvneuserback.userApi.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.nvneuserback.userApi.dao.CollaborateurRepository;
import fr.nvneuserback.userApi.dao.ConfirmationTokenRepository;
import fr.nvneuserback.userApi.entities.Collaborateur;
import fr.nvneuserback.userApi.entities.ConfirmationToken;
import fr.nvneuserback.userApi.entities.Entreprise;
import fr.nvneuserback.userApi.services.CollaborateurService;
import fr.nvneuserback.userApi.services.EmailSenderService;
@RestController
@CrossOrigin("*")
public class CollaborateurRestController {
	
	@Autowired
	private CollaborateurService collaborateurService;
	
	@Autowired
	private CollaborateurRepository collaborateurRepository;

    public CollaborateurRestController(CollaborateurRepository collaborateurRepository) {
//        this.collaborateurRepository = collaborateurRepository;
    }
    
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping(path="/api/collaborateurs/uploadCV", consumes = "multipart/form-data")
    @ResponseBody
    public ResponseEntity<String> uploadCV(@RequestPart("cv") MultipartFile file, @RequestPart("collaborateur") Collaborateur collaborateur ) throws IOException {
        String filename = file.getOriginalFilename()+" "+System.currentTimeMillis()+".pdf";
        Path path = Paths.get(System.getProperty("user.home")+"/upload/cv/"+filename);
        Files.write(path, file.getBytes());
        collaborateur.setCv(path.getFileName().toString());
//        collaborateurRepository.save(collaborateur);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/api/collaborateurs/downloadCV/{fileName:.+}")
    public void downloadCV( HttpServletRequest request, 
                                     HttpServletResponse response, 
                                     @PathVariable("fileName") String fileName) 
    {
        //If user is not authorized - he should be thrown out from here itself
         
        //Authorized user will download the file
        // String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads/pdf/");
        Path file = Paths.get(System.getProperty("user.home")+"/upload/cv/"+fileName);
        if (Files.exists(file)) 
        {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @RequestMapping(value="/api/prendreRDVCollaborateur",method = RequestMethod.PATCH)
	public Collaborateur prendreRDVCollaborateur(@RequestBody Collaborateur e) {
    	e.getDateContact().setHours(Integer.parseInt(e.getHeureFormContact().substring(0, 2)));
    	e.getDateContact().setMinutes(Integer.parseInt(e.getHeureFormContact().substring(3)));
//    	e.setId(entrepriseRepository.findByEmail(e.getEmail()).getId());
        return collaborateurRepository.save(e);
    }
    
    @PostMapping("/api/registerCollaborateur")
	public Collaborateur register(@RequestBody Collaborateur collaborateur) {
//    	Collaborateur collaborateur = ;
		if (collaborateurService.findByEmail(collaborateur.getEmail()) != null)
			throw new RuntimeException("This user already exists");
//		collaborateur = new Collaborateur();
		collaborateur.setDateInscription(new Date());
        collaborateur =  collaborateurService.registerCollaborateur(collaborateur);
        ConfirmationToken confirmationToken = new ConfirmationToken();

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(collaborateur.getEmail());
        mailMessage.setSubject("NVNE - Confirmation d'inscription.");
        mailMessage.setFrom("nvne.brest@gmail.com");
        mailMessage.setText("Pour confirmer votre adresse email, cliquez sur le lien ci-après. Si vous n'avez pas déclanchez ce processus, veuillez ignorer ce mail. "
        +"http://localhost:8082/confirm-account?token="+confirmationToken.getConfirmationToken());

//        emailSenderService.sendEmail(mailMessage);
        
        return collaborateur;
    }
}
