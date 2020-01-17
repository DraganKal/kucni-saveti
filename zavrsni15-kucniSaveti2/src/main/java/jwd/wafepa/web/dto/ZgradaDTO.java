package jwd.wafepa.web.dto;

public class ZgradaDTO {

	
	private Long id;
	
	private String adresa;
	
	private String predsednikKs;
	
	private int brojStanova;
	
	private int brojStanara;

	public ZgradaDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	
	
}
