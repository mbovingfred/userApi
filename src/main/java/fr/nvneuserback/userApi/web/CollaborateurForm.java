package fr.nvneuserback.userApi.web;

import lombok.Data;

@Data
public class CollaborateurForm {
	private String nom;
	private String prenom;
	private String dateDeNaissance;
	private String tel;
	private String email;
	private String ackAge;
	private String ackCgu;
	public CollaborateurForm() {
		super();
	}
	public CollaborateurForm(String nom, String prenom, String dateDeNaissance, String tel, String email, String ackAge,
			String ackCgu) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.tel = tel;
		this.email = email;
		this.ackAge = ackAge;
		this.ackCgu = ackCgu;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDateDeNaissance() {
		return dateDeNaissance;
	}
	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAckAge() {
		return ackAge;
	}
	public void setAckAge(String ackAge) {
		this.ackAge = ackAge;
	}
	public String getAckCgu() {
		return ackCgu;
	}
	public void setAckCgu(String ackCgu) {
		this.ackCgu = ackCgu;
	}
}
