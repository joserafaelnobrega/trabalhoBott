
public class Cadastro_Bem {
	private String nome_bem;
	private String descricao_bem;
	private String codigo_bem;
	private Cadastro_Local localizacao  = new Cadastro_Local();
	private Cadastro_Categoria categoria  = new Cadastro_Categoria();
	
	
	public void setNome(String nome) { 
    	
    	this.nome_bem = nome; 
    }
	
	  public String getNome() { 
	    	
	    	return this.nome_bem; 
	    }
	  
	  public void setCodigo(String cod) {
	        codigo_bem = cod;
	    }
	
	
	public String getCodigo() { 
	    
    	return codigo_bem; 
    }
    
   
	public void setDescricao(String descricao) {
	    	
	    	this.descricao_bem = descricao; 
	  }
    
    public String getDescricao() { 
    	
    	return descricao_bem; 
    }

  

 

   

}
