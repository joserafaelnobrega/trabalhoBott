import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;



public class Cadastro_Local {
		private String Local_nome;
		private String Descricao_local;
	    private FileWriter arq;
	    private PrintWriter gravarArq ;
		
		
			
		
		public void setLocal(String local) {
			this.Local_nome = local;
		}
		
		public String getLocal() {
			return this.Local_nome;
		}
		
		public void setDescricaoLocal(String Descri) {
			this.Descricao_local = Descri;
		}
		
		public String getDescricaoLocal() {
			return this.Descricao_local;
		}
		
		
		//metodo responsavel por criar o arquivo e escrever nele 
		public void criar() {
			try {
				File file = new File("locais/"+ this.Local_nome	);
				file.mkdir();
			
			arq = new FileWriter( "locais/"+ this.Local_nome + "/@");
			gravarArq = new PrintWriter( arq);
			gravarArq.printf("Descrição: " + this.Descricao_local);
			gravarArq.printf("#");
			arq.close();
			}   catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		                e.getMessage());
		          }
		}
		
		
		
		public void listarloc() {
			Enviarmsg enviar = new Enviarmsg();
			Receber_msg captura_dmensagens = new Receber_msg();
			
			File file = new File("locais"); // por o nome do diretorio ali
			File afile[] = file.listFiles();
			int i = 0;
			
			for (int j = afile.length; i < j; i++) {
				File arquivos = afile[i];
			
				
				
				enviar.montarMsg( captura_dmensagens.up() , arquivos.getName());//ate aqui eu listo o nome da pasta
				
				
			File pasta = new File("locais/" +arquivos.getName() );//entrando dentro do diretorio com o nome do local
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
}//fim classe
