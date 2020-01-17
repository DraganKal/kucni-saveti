package jwd.wafepa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Zgrada {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable = false, unique = true)
	private String adresa;
	@Column(nullable = false)
	private String predsednikKs;
	@Column
	private int brojStanova;
	@Column(nullable = false)
	private int brojStanara;
	
	@OneToMany(mappedBy="zgrada", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Poruka> poruke = new ArrayList<>();
	public Zgrada() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Zgrada(String adresa, String predsednikKs, int brojStanova, int brojStanara) {
		super();
		this.adresa = adresa;
		this.predsednikKs = predsednikKs;
		this.brojStanova = brojStanova;
		this.brojStanara = brojStanara;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getPredsednikKs() {
		return predsednikKs;
	}
	public void setPredsednikKs(String predsednikKs) {
		this.predsednikKs = predsednikKs;
	}
	public int getBrojStanova() {
		return brojStanova;
	}
	public void setBrojStanova(int brojStanova) {
		this.brojStanova = brojStanova;
	}
	public int getBrojStanara() {
		return brojStanara;
	}
	public void setBrojStanara(int brojStanara) {
		this.brojStanara = brojStanara;
	}
	public List<Poruka> getPoruke() {
		return poruke;
	}
	public void setPoruke(List<Poruka> poruke) {
		this.poruke = poruke;
	}
	public void addPoruka(Poruka poruka) {
		if(poruka.getZgrada() != this) {
			poruka.setZgrada(this);
		}
		poruke.add(poruka);
	}
	
	

}
