package fr.nvneuserback.userApi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entreprise {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomEntreprise;
	private String nom;
	private String prenom;
	private String email;
	private String tel;
	private boolean ackCgu;
	private boolean ackCallBack;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomEntreprise() {
		return nomEntreprise;
	}
	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public boolean isAckCgu() {
		return ackCgu;
	}
	public void setAckCgu(boolean ackCgu) {
		this.ackCgu = ackCgu;
	}
	public boolean isAckCallBack() {
		return ackCallBack;
	}
	public void setAckCallBack(boolean ackCallBack) {
		this.ackCallBack = ackCallBack;
	}
	public Entreprise(Long id, String nomEntreprise, String nom, String prenom, String email, String tel,
			boolean ackCgu, boolean ackCallBack) {
		super();
		this.id = id;
		this.nomEntreprise = nomEntreprise;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.ackCgu = ackCgu;
		this.ackCallBack = ackCallBack;
	}
	public Entreprise() {
		super();
	}
	
}