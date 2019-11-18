

//essa classe recebe os comandos do bot e em seguida fornece as ações solicitadas pelo comando selecionado
public class Comandos {
	
	private Cadastro_Local local = new Cadastro_Local();
	private Cadastro_Categoria categoria = new Cadastro_Categoria();
	private Cadastro_Bem bem = new Cadastro_Bem();
	private String comandos = "/cadastrar_localizacao\n/cadastrar_categ\n/cadastrar_bem \n/listar_localizacao \n/listar_categorias\n/listar_bens_de_uma_localizacao\n"
			+ "/buscar_bem_por_codigo\n/buscar_bem_por_nome\n/buscar_bem_por_descricao\n/movimentar_bem\n/gerar_relatorio";
	
	
	
	//recebe os comandos e executa as ações de acordo com o que foi solicitado
	public void Receptor() {
	
		Receber_msg captura_dmensagens = new Receber_msg();
		Enviarmsg enviar = new Enviarmsg();
		
		
		
		
		String msgcaptura = captura_dmensagens.recebido();
		
			//se digitar help esse comando é invocado
			if(msgcaptura.equals("/help") ) {
			
				enviar.montarMsg( captura_dmensagens.up() , comandos);
				
				msgcaptura = "";
			//comando cadastrar localização
			}else if(msgcaptura.equals("/cadastrar_localizacao")) {
				
			enviar.montarMsg(captura_dmensagens.up(), "digite o nome da localização:");
			
			do {
				
			local.setLocal(captura_dmensagens.recebido());
			
			}while(local.getLocal().equals(""));
			
			enviar.montarMsg(captura_dmensagens.up(), "digite a descrição:");
			
				do {
				
				local.setDescricaoLocal(captura_dmensagens.recebido() );
				
				}while(local.getDescricaoLocal().equals(""));
				
			local.criar();	
			
			enviar.montarMsg(captura_dmensagens.up(), "LOCALIZAÇÃO CADASTRADA!!!" );
			
			
			
			msgcaptura = "";
			//cadastra a categoria
			}else if(msgcaptura.equals("/cadastrar_categ") ) {
				
				enviar.montarMsg(captura_dmensagens.up(), "digite o nome da categoria:");
				
				
				do {
					
					categoria.setCategoria(captura_dmensagens.recebido());
					
					}while(categoria.getCategoria().equals(""));
					
				enviar.montarMsg(captura_dmensagens.up(), "digite a descrição:");
				
				do {
					
					categoria.setDescricaoCategoria(captura_dmensagens.recebido());
					
					}while(categoria.getDescriçãoCategoria().equals(""));
				
				enviar.montarMsg(captura_dmensagens.up(), "digite o codigo da categoria:");
				
				do {
					
					categoria.setCodigocat(captura_dmensagens.recebido());
					
					}while(categoria.getCodigocat().equals(""));
				
				categoria.criar();
				
				enviar.montarMsg(captura_dmensagens.up(), "CATEGORIA CADASTRADA COM SUCESSO!");
				
				msgcaptura = "";
				//cadastrar bem 
			}else if(msgcaptura.equals("/cadastrar_bem")  ){
				String localizacao_bem = "";
				String categoria_bem =  "";
				
				
						enviar.montarMsg(captura_dmensagens.up(), "digite o nome do Bem:");
				
				
				do {
					
					bem.setNome(captura_dmensagens.recebido());
					
					}while(bem.getNome().equals(""));
				
				enviar.montarMsg(captura_dmensagens.up(), "digite a descrição do bem:");
				
				
					do {
					
					bem.setDescricao(captura_dmensagens.recebido());
					
					}while(bem.getDescricao().equals(""));
					
					
					enviar.montarMsg(captura_dmensagens.up(), "digite o codigo do bem:");
						
					do {
						
						bem.setCodigo(captura_dmensagens.recebido());
						
						}while(bem.getCodigo().equals(""));
					
					enviar.montarMsg(captura_dmensagens.up(), "informe a localização do bem:");
					
					do {
						
						localizacao_bem  = captura_dmensagens.recebido();
						
						}while(localizacao_bem.equals(""));
					
					
						enviar.montarMsg(captura_dmensagens.up(), "informe a categoria do bem:");
					
					do {
						
						categoria_bem  = captura_dmensagens.recebido();
						
						}while(categoria_bem.equals(""));
					
					bem.criar(localizacao_bem , categoria_bem);
					
					/* while(bem.criar(localizacao_bem , categoria_bem) != 0 ) {
					
					if(bem.criar(localizacao_bem , categoria_bem) == 1 )  {
						
						
						enviar.montarMsg(captura_dmensagens.up(), "localização nao encontrada digite uma das seguintes:");
						local.listarloc();
						
						do {
							
							localizacao_bem  = captura_dmensagens.recebido();
							
							}while(localizacao_bem.equals(""));
						
						
						
						
					}else{
						
						enviar.montarMsg(captura_dmensagens.up(), "informe a categoria do bem:");
						categoria.listarcat();
						
						do {
							
							categoria_bem  = captura_dmensagens.recebido();
							
							}while(categoria_bem.equals(""));
						
					}//fim if
				
					*///fim while

						enviar.montarMsg(captura_dmensagens.up(), "BEM CADASTRADO COM SUCESSO!!!");
					
					
					
					
				
					
				
				msgcaptura = "";
				//listar localização
			}else if(msgcaptura.equals("/listar_localizacao") ){
				
				local.listarloc();
				msgcaptura = "";
				
				//listar categoria
			}else if( msgcaptura.equals("/listar_categorias") ) {
				categoria.listarcat();
				msgcaptura = "";
			}
			
			
		
			
		
}//fim metodo
	
}	//fim classe
	
		

