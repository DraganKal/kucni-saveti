package jwd.wafepa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Poruka {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable = false)
	private String naslov;
	@Column
	private String tip;
	@Column
	private double potrebanProcenat;
	@Column
	private boolean prihvacen;
	@Column(nullable = false)
	private String opis;
	@ManyToOne(fetch=FetchType.EAGER)
	private Zgrada zgrada;
	@OneToMany(mappedBy="poruka", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Glas> glasovi = new ArrayList<>();
	@Column
	private int glasoviZa;
	public Poruka() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public double getPotrebanProcenat() {
		return potrebanProcenat;
	}
	public void setPotrebanProcenat(double potrebanProcenat) {
		this.potrebanProcenat = potrebanProcenat;
	}
	public boolean isPrihvacen() {
		return prihvacen;
	}
	public void setPrihvacen(boolean prihvacen) {
		this.prihvacen = prihvacen;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public Zgrada getZgrada() {
		return zgrada;
	}
	public void setZgrada(Zgrada zgrada) {
		this.zgrada = zgrada;
		if(!zgrada.getPoruke().contains(this)){
			zgrada.getPoruke().add(this);
		}
	}
	public List<Glas> getGlasovi() {
		return glasovi;
	}
	public void setGlasovi(List<Glas> glasovi) {
		this.glasovi = glasovi;
	}
	
	public void addGlas(Glas glas) {
		if(glas.getPoruka() != this) {
			glas.setPoruka(this);
		}
		glasovi.add(glas);
	}
	public int getGlasoviZa() {
		return glasoviZa;
	}
	public void setGlasoviZa(int glasoviZa) {
		this.glasoviZa = glasoviZa;
	}

	

}
