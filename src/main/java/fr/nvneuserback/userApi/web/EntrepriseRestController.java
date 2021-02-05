package fr.nvneuserback.userApi.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import fr.nvneuserback.userApi.dao.EntrepriseRepository;
import fr.nvneuserback.userApi.entities.Entreprise;
import fr.nvneuserback.userApi.services.EntrepriseService;
@RestController
@CrossOrigin("*")
public class EntrepriseRestController {
	
	@Autowired
	private EntrepriseService entrepriseService;
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;

    @PostMapping(path="/api/entreprises/uploadCV", consumes = "multipart/form-data")
    @ResponseBody
    public ResponseEntity<String> uploadCV(@RequestPart("cv") MultipartFile file, @RequestPart("entreprise") Entreprise entreprise ) throws IOException {
        String filename = file.getOriginalFilename()+" "+System.currentTimeMillis()+".pdf";
        Path path = Paths.get(System.getProperty("user.home")+"/upload/cv/"+filename);
        Files.write(path, file.getBytes());
//        entreprise.setCv(path.getFileName().toString());
//        entrepriseRepository.save(entreprise);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/api/entreprises/downloadCV/{fileName:.+}")
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
    @RequestMapping(value="/api/users/register/inscripEntreprise",method = RequestMethod.POST)
	public Entreprise register(@RequestBody Entreprise e) {
//    	System.out.println("Registering a new enterprise ...");
//    	Entreprise entreprise = entrepriseService.findByEmail(entrepriseForm.getEmail());
//		if (entreprise != null)
//			throw new RuntimeException("This user already exists");
//		entreprise = new Entreprise();
    	e.setDateInscription(new Date());
        return entrepriseRepository.save(e);
    }
    

    @RequestMapping(value="/api/users/register/prendreRDVEntreprise",method = RequestMethod.PATCH)
	public Entreprise prendreRDVEntreprise(@RequestBody Entreprise e) throws Exception {
    	System.out.println("dateFromMillis: "+new Date(Long.parseLong(e.getDateFormContact())));
//    	System.out.println("heure: "+Integer.parseInt(e.getHeureFormContact().substring(0, 2))+Integer.parseInt(e.getHeureFormContact().substring(3)));
    	System.out.println("date: "+e.getDateContact());
//        Entreprise entreprise = entrepriseRepository.findById(e.getId()).orElseThrow(() -> new Exception("Could not find Resource"));
    	e.setDateContact(new Date(Long.parseLong(e.getDateFormContact())));
//    	entreprise.getDateContact().setHours(Integer.parseInt(e.getHeureFormContact().substring(0, 2)));
//    	entreprise.getDateContact().setMinutes(Integer.parseInt(e.getHeureFormContact().substring(3)));
//    	e.setId(entrepriseRepository.findByEmail(e.getEmail()).getId());
        return entrepriseRepository.save(e);
    }
    
    @RequestMapping(value="/api/users/register/entrepris",method = RequestMethod.GET)
	public List<Entreprise> entrepris() {
//    	System.out.println("Registering a new enterprise ...");
//    	Entreprise entreprise = entrepriseService.findByEmail(entrepriseForm.getEmail());
//		if (entreprise != null)
//			throw new RuntimeException("This user already exists");
//		entreprise = new Entreprise();
        return entrepriseRepository.findAll();
    }
    
    @RequestMapping(value="/",method = RequestMethod.GET)
	public ResponseEntity entrepri() {
//    	System.out.println("Registering a new enterprise ...");
//    	Entreprise entreprise = entrepriseService.findByEmail(entrepriseForm.getEmail());
//		if (entreprise != null)
//			throw new RuntimeException("This user already exists");
//		entreprise = new Entreprise();
        return ResponseEntity.ok().build();
    }
}
