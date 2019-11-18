import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Cadastro_Bem {
	private String nome_bem;
	private String descricao_bem;
	private String codigo_bem;
	private Cadastro_Local localizacao  = new Cadastro_Local();
	private Cadastro_Categoria categoria  = new Cadastro_Categoria();
	private FileWriter arq;
	private PrintWriter gravarArq ;
	
	
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

  
//criar bem 
    public int  criar(String nomelocal,  String nomecategoria) {
    	try {
			File file = new File("locais/"+nomelocal	);
			file.mkdir();
		
		arq = new FileWriter( "locais/"+ nomelocal +"/"+ this.nome_bem);
		gravarArq = new PrintWriter( arq);
		gravarArq.printf("Descrição: " + this.descricao_bem);
		gravarArq.printf("codigo: " + this.codigo_bem);
		gravarArq.printf("localização: " + nomelocal);
		gravarArq.printf("categoria: "+ nomecategoria);
		 
		gravarArq.printf("#");
		arq.close();
		}   catch (IOException e) {
			
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	                e.getMessage());
	        return 1;
	          }
    	
    	
    	try {
			File file = new File("categorias/"+nomecategoria	);
			file.mkdir();
		
		arq = new FileWriter( "categorias/"+ nomecategoria +"/"+ this.nome_bem);
		gravarArq = new PrintWriter( arq);
		gravarArq.printf("Descrição: " + this.descricao_bem +"\n");
		gravarArq.printf("codigo: " + this.codigo_bem +"\n");
		gravarArq.printf("localização: " + nomelocal +"\n");
		gravarArq.printf("categoria: "+ nomecategoria);
		 
		gravarArq.printf("#");
		arq.close();
		}   catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	                e.getMessage());
	        return 2;
	          }
    	return 0;
    	
    		}//fim do metodo
 

   

}
