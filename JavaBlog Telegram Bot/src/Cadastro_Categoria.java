import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class Cadastro_Categoria{
	private String categoria_nome;
	private String Descricao_categoria;
	private String codigo;
	private FileWriter arq;
	private PrintWriter gravarArq;
	public void setCategoria(String categoria) {
		this.categoria_nome = categoria;
	}
	
	public String getCategoria() {
		return this.categoria_nome;
	}
	
	public void setDescricaoCategoria(String Descri) {
		this.Descricao_categoria = Descri;
	}
	
	public String getDescriçãoCategoria() {
		return this.Descricao_categoria;
	}
	
	public void setCodigocat(String cod) {
		this.codigo = cod;
	}
	
	public String getCodigocat() {
		return this.codigo;
	}
	
//metodo que cria o arquivo e escreve nele 
	public void criar()  {
		try {
			File file = new File("categorias/"+ this.categoria_nome);
			file.mkdir();
			
			arq = new FileWriter("categorias/" +this.categoria_nome+"/@");
			gravarArq = new PrintWriter( arq);
			gravarArq.printf("Descrição: " + this.Descricao_categoria + "\n");
			gravarArq.printf("Codigo: " + this.codigo);
			gravarArq.printf("#");
			arq.close();
			}   catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		                e.getMessage());
		          }
		
	}
	

	
	public void listarcat()  {
		Enviarmsg enviar = new Enviarmsg();
		Receber_msg captura_dmensagens = new Receber_msg();
		
		File file = new File("categorias"); // por o nome do diretorio ali
		File afile[] = file.listFiles();
		Arrays.sort(afile);
		int i = 0;
		
		for (int j = afile.length; i < j; i++) {
			File arquivos = afile[i];
		
			
			
			enviar.montarMsg( captura_dmensagens.up() , arquivos.getName());//ate aqui eu listo o nome da pasta
			
			
		File pasta = new File("categorias/" +arquivos.getName() );//entrando dentro do diretorio com o nome do local
		File pasta2[] = pasta.listFiles();// copiando das as pastas do diretorio
		
		int m = 0;
		for (int f = pasta2.length; m< f; m++) {
			File arquivo = pasta2[m];
		
		
			if(arquivo.getName().equals("@")) {
			  try {
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
		
		
		
			
			
			
			
		 
		
	}//fim for
}//fim metodo
	
	

}
