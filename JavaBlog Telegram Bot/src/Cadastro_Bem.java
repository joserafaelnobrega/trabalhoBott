import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

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
		gravarArq.printf("Descrição: " + this.descricao_bem + "\n");
		gravarArq.printf("codigo: " + this.codigo_bem + "\n");
		gravarArq.printf("localização: " + nomelocal + "\n");
		gravarArq.printf("categoria: "+ nomecategoria + "\n");
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
		gravarArq.println("Descrição: " + this.descricao_bem + "\n");
		gravarArq.println("codigo: " + this.codigo_bem + "\n" );
		gravarArq.println("localização: " + nomelocal + "\n");
		gravarArq.println("categoria: "+ nomecategoria +" \n");
		gravarArq.printf("#");
		arq.close();
		}   catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	                e.getMessage());
	        return 2;
	          }
    	
    	try {
			File file = new File("itens/"+ this.nome_bem);
			file.mkdir();
			
			arq = new FileWriter("itens/" +this.nome_bem+"/@");
			gravarArq = new PrintWriter( arq);
			gravarArq.printf("Descrição: " + this.descricao_bem + "\n");
			gravarArq.printf("Codigo: " + this.codigo_bem + "\n");
			gravarArq.printf("localização: " + nomelocal + "\n");
			gravarArq.printf("categoria: "+ nomecategoria + "\n");
			gravarArq.printf("#");
			arq.close();
			}   catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		                e.getMessage());
		          }
    
		
	
    	
    	
    	
    	return 0;
    	
    		}//fim do metodo
    
 public void listarbemcodigo(String l) {
	 Enviarmsg enviar = new Enviarmsg();
		Receber_msg captura_dmensagens = new Receber_msg();
		String nome= "";
		
		File file = new File("itens"); // por o nome do diretorio ali
		File afile[] = file.listFiles();
		int i = 0;
		int flag = 0;
	 
		for (int j = afile.length; i < j; i++) {
			File arquivos = afile[i];
			
			
			File pasta = new File("itens/" +arquivos.getName() );//entrando dentro do diretorio com o nome do local
			File pasta2[] = pasta.listFiles();// copiando das as pastas do diretorio
			
			try {
				  FileReader arq = new FileReader(pasta2[0]);
			      BufferedReader lerArq = new BufferedReader(arq);
			 
			      String linha = ""; 
			      int cont = 0;
			      
			  
			     while(cont < 2) {
			      
			      cont++;
			      linha = lerArq.readLine(); 
			      if(linha.contains(l) ) {
			    	  enviar.montarMsg( captura_dmensagens.up() ,"item encontrado " +linha);
					 linha = lerArq.readLine(); 
					 enviar.montarMsg( captura_dmensagens.up() , linha);
					 flag = 1;
					 
			     }//fim if
			      
			      
			     }//fim while
	
			     
				  } catch (IOException e) {
				        System.err.printf("Erro na abertura do arquivo: %s.\n",
				          e.getMessage());
				    }//fim catch
			
			
		}//fim for
	 if(flag == 0) {
		 enviar.montarMsg( captura_dmensagens.up() ,"item nao encontrado");
	 }
	 
 } //fim metodo   
    
    
    
    
 
public void listarBensLoc(String nomeloc) {
	Enviarmsg enviar = new Enviarmsg();
	Receber_msg captura_dmensagens = new Receber_msg();
	
	File file = new File("locais"); // por o nome do diretorio ali
	File afile[] = file.listFiles();
	Arrays.sort(afile);
	int i = 0;
	
	for (int j = afile.length; i < j; i++) {
		File arquivos = afile[i];
	
		if(nomeloc.equals(arquivos.getName() ) ) { 
		
		enviar.montarMsg( captura_dmensagens.up() , arquivos.getName()+ ":\n");//ate aqui eu listo o nome da pasta
		
		
	File pasta = new File("locais/" +arquivos.getName() );//entrando dentro do diretorio com o nome do local
	
	
	
	
	File pasta2[] = pasta.listFiles();// copiando das as pastas do diretorio
	
	int m = 0;
	for (int f = pasta2.length; m< f; m++) {
		File arquivo = pasta2[m];
	
	
		if(!arquivo.getName().equals("@")) {
		  try {
			  enviar.montarMsg( captura_dmensagens.up() , arquivo.getName());
		  FileReader arq = new FileReader(arquivo);
	      BufferedReader lerArq = new BufferedReader(arq);
	 
	      String linha = lerArq.readLine(); 
	      int cont = 0;
	     while(cont != 1) {
	      
				      char simbolo = '#';
				      char[] palavra = linha.toCharArray();
				    
				      for (int k = 0; k < palavra.length; k++) {
				          if (palavra[k] == simbolo) {
				              cont++;
				          }//fim if
				      }//fim if
	      
	      
	      enviar.montarMsg( captura_dmensagens.up() , linha);
			 
	        linha = lerArq.readLine(); // lê da segunda até a última linha
	      
	      
	     }//fim while
	     
	      

		 
	 
	     
		  } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		    }//fim catch
		
		}//fim if
	}//fim for
	
	
	
		
		
		}//fim if	
		
		
	
}//fim for

}//fim metodo


public void buscarnome(String N) {
	Enviarmsg enviar = new Enviarmsg();
	Receber_msg captura_dmensagens = new Receber_msg();
	
	File file = new File("itens"); // por o nome do diretorio ali
	File afile[] = file.listFiles();
	int i = 0;
	int flag  = 0;
	
	for (int j = afile.length; i < j; i++) {
		File arquivos = afile[i];
	
		if(N.equals(arquivos.getName() ) ) {
			 
		      enviar.montarMsg( captura_dmensagens.up() , "item encontrado");
		      	flag  = 1;
		}
	}//fim for
	if(flag == 0 ) {
	     enviar.montarMsg( captura_dmensagens.up() , "item não encontrado");
	}
}//fim metodo
   

public void buscardescri(String l) {
	 Enviarmsg enviar = new Enviarmsg();
		Receber_msg captura_dmensagens = new Receber_msg();
		String nome= "";
		
		File file = new File("itens"); // por o nome do diretorio ali
		File afile[] = file.listFiles();
		int i = 0;
		int flag = 0;
	 
		for (int j = afile.length; i < j; i++) {
			File arquivos = afile[i];
			
			
			File pasta = new File("itens/" +arquivos.getName() );//entrando dentro do diretorio com o nome do local
			File pasta2[] = pasta.listFiles();// copiando das as pastas do diretorio
			
			try {
				  FileReader arq = new FileReader(pasta2[0]);
			      BufferedReader lerArq = new BufferedReader(arq);
			 
			      String linha = ""; 
			      int cont = 0;
			      
			  
			     
			      
			      
			      linha = lerArq.readLine(); 
			      if(linha.contains(l) ) {
			    	  enviar.montarMsg( captura_dmensagens.up() ,"item encontrado " );
					 
					 flag = 1;
					 
			     }//fim if
			      
			      
			   
	
			     
				  } catch (IOException e) {
				        System.err.printf("Erro na abertura do arquivo: %s.\n",
				          e.getMessage());
				    }//fim catch
			
			
		}//fim for
	 if(flag == 0) {
		 enviar.montarMsg( captura_dmensagens.up() ,"item nao encontrado");
	 }
	 
} //fim metodo   


public void listarB() {
	Enviarmsg enviar = new Enviarmsg();
	Receber_msg captura_dmensagens = new Receber_msg();
	
	File file = new File("locais"); // por o nome do diretorio ali
	File afile[] = file.listFiles();
	Arrays.sort(afile);
	int i = 0;
	
	enviar.montarMsg( captura_dmensagens.up() , "LOCAIS ->");
	
	for (int j = afile.length; i < j; i++) {
		File arquivos = afile[i];
	
		
		
		enviar.montarMsg( captura_dmensagens.up() , "LOCAL - " + arquivos.getName()+ ":\n");//ate aqui eu listo o nome da pasta
		
		
	File pasta = new File("locais/" +arquivos.getName() );//entrando dentro do diretorio com o nome do local
	
	
	
	
	File pasta2[] = pasta.listFiles();// copiando das as pastas do diretorio
	Arrays.sort(pasta2);
	int m = 0;
	for (int f = pasta2.length; m< f; m++) {
		File arquivo = pasta2[m];
	
	
		
		  try {
			  enviar.montarMsg( captura_dmensagens.up() ,  arquivo.getName());
		  FileReader arq = new FileReader(arquivo);
	      BufferedReader lerArq = new BufferedReader(arq);
	 
	      String linha = lerArq.readLine(); 
	      int cont = 0;
	     while(cont != 1) {
	      
				      char simbolo = '#';
				      char[] palavra = linha.toCharArray();
				    
				      for (int k = 0; k < palavra.length; k++) {
				          if (palavra[k] == simbolo) {
				              cont++;
				          }//fim if
				      }//fim if
	      
	      
	      enviar.montarMsg( captura_dmensagens.up() , linha);
			 
	        linha = lerArq.readLine(); // lê da segunda até a última linha
	      
	      
	     }//fim while
	     

	     
		  } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		    }//fim catch
		
		
	}//fim for
	

	
}//fim for

}//fim metodo

public void listarC() {
	Enviarmsg enviar = new Enviarmsg();
	Receber_msg captura_dmensagens = new Receber_msg();
	
	File file = new File("categorias"); // por o nome do diretorio ali
	File afile[] = file.listFiles();
	Arrays.sort(afile);
	int i = 0;
	
	enviar.montarMsg( captura_dmensagens.up() , "CATEGORIAS ->");
	
	for (int j = afile.length; i < j; i++) {
		File arquivos = afile[i];
	
		
		
		enviar.montarMsg( captura_dmensagens.up() , " CATEGORIA - " +arquivos.getName()+ ":\n");//ate aqui eu listo o nome da pasta
		
		
	File pasta = new File("categorias/" +arquivos.getName() );//entrando dentro do diretorio com o nome do local
	
	
	
	
	File pasta2[] = pasta.listFiles();// copiando das as pastas do diretorio
	Arrays.sort(pasta2);
	int m = 0;
	for (int f = pasta2.length; m< f; m++) {
		File arquivo = pasta2[m];
	
	
		
		  try {
			  enviar.montarMsg( captura_dmensagens.up() ,  arquivo.getName());
		  FileReader arq = new FileReader(arquivo);
	      BufferedReader lerArq = new BufferedReader(arq);
	 
	      String linha = lerArq.readLine(); 
	      int cont = 0;
	     while(cont != 1) {
	      
				      char simbolo = '#';
				      char[] palavra = linha.toCharArray();
				    
				      for (int k = 0; k < palavra.length; k++) {
				          if (palavra[k] == simbolo) {
				              cont++;
				          }//fim if
				      }//fim if
	      
	      
	      enviar.montarMsg( captura_dmensagens.up() , linha);
			 
	        linha = lerArq.readLine(); // lê da segunda até a última linha
	      
	      
	     }//fim while
	     

	     
		  } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		    }//fim catch
		
		
	}//fim for
	

	
}//fim for

}//fim metodo

public void listarI() {
	Enviarmsg enviar = new Enviarmsg();
	Receber_msg captura_dmensagens = new Receber_msg();
	
	File file = new File("itens"); // por o nome do diretorio ali
	File afile[] = file.listFiles();
	Arrays.sort(afile);
	int i = 0;
	
	enviar.montarMsg( captura_dmensagens.up() , "ITENS ->");
	
	for (int j = afile.length; i < j; i++) {
		File arquivos = afile[i];
	
		
		
		enviar.montarMsg( captura_dmensagens.up() ,"ITEM - " +arquivos.getName()+ ":\n");//ate aqui eu listo o nome da pasta
		
		
	File pasta = new File("itens/" +arquivos.getName() );//entrando dentro do diretorio com o nome do local
	
	
	
	
	File pasta2[] = pasta.listFiles();// copiando das as pastas do diretorio
	Arrays.sort(pasta2);
	int m = 0;
	for (int f = pasta2.length; m< f; m++) {
		File arquivo = pasta2[m];
	
	
		
		  try {
			  enviar.montarMsg( captura_dmensagens.up() , arquivo.getName());
		  FileReader arq = new FileReader(arquivo);
	      BufferedReader lerArq = new BufferedReader(arq);
	 
	      String linha = lerArq.readLine(); 
	      int cont = 0;
	     while(cont != 1) {
	      
				      char simbolo = '#';
				      char[] palavra = linha.toCharArray();
				    
				      for (int k = 0; k < palavra.length; k++) {
				          if (palavra[k] == simbolo) {
				              cont++;
				          }//fim if
				      }//fim if
	      
	      
	      enviar.montarMsg( captura_dmensagens.up() , linha);
			 
	        linha = lerArq.readLine(); // lê da segunda até a última linha
	      
	      
	     }//fim while
	     

	     
		  } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		    }//fim catch
		
		
	}//fim for
	

	
}//fim for

}//fim metodo



}
