package jwd.wafepa.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class PorukaDTO {
	
	private Long id;
	@NotEmpty
	private String naslov;
	
	private String tip;
	@Min(0)
	@Max(100)
	private double potrebanProcenat;
	
	private boolean prihvacen;
	
	private String opis;
	
	private Long zgradaId;
	private String zgradaNaziv;
	public PorukaDTO() {
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
	public Long getZgradaId() {
		return zgradaId;
	}
	public void setZgradaId(Long zgradaId) {
		this.zgradaId = zgradaId;
	}
	public String getZgradaNaziv() {
		return zgradaNaziv;
	}
	public void setZgradaNaziv(String zgradaNaziv) {
		this.zgradaNaziv = zgradaNaziv;
	}
	
	
	
}
