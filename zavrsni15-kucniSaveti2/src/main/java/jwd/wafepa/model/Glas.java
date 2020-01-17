package jwd.wafepa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Glas {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String predlogPodrzan;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Poruka poruka;

	public Glas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPredlogPodrzan() {
		return predlogPodrzan;
	}

	public void setPredlogPodrzan(String predlogPodrzan) {
		this.predlogPodrzan = predlogPodrzan;
	}

	public Poruka getPoruka() {
		return poruka;
	}

	public void setPoruka(Poruka poruka) {
		this.poruka = poruka;
		if(!poruka.getGlasovi().contains(this)){
			poruka.getGlasovi().add(this);
		}
	}
	
	
	

	
}
