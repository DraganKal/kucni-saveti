package jwd.wafepa.web.dto;


public class GlasDTO {
	
	private Long id;
	
	private String predlogPodrzan;
	
	
	private Long porukaId;
	private String porukaNaziv;
	public GlasDTO() {
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
	public Long getPorukaId() {
		return porukaId;
	}
	public void setPorukaId(Long porukaId) {
		this.porukaId = porukaId;
	}
	public String getPorukaNaziv() {
		return porukaNaziv;
	}
	public void setPorukaNaziv(String porukaNaziv) {
		this.porukaNaziv = porukaNaziv;
	}
	
	
	
	

}
