import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
//import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
//import com.pengrad.telegrambot.response.SendResponse;

public class Receber_msg {
			//Criação do objeto bot com as informações de acesso
		private TelegramBot bot = TelegramBotAdapter.build("1041357533:AAEZF5cos0qU6MDm5-LTlLLtuOGETGwGuGk");
			//objeto responsável por receber as mensagens
		private 	GetUpdatesResponse updatesResponse;
			
	
		//responsavel por capturar a proxima mensagem
		static private int m  ;
			
			//metodo que vai pegar tudo que foi recebido
public String recebido() {			
					//mensagem de retorno com o que foi digitado
				String msg = "";
				
				//captura doq foi digitado
				
				updatesResponse =  bot.execute(new GetUpdates().offset(m));
	
				if(updatesResponse.isOk() ) {
				//cria uma lista com tudo que foi capturado
				
				
				 List<Update> updates = updatesResponse.updates();
				 
				 
				//vai percorrendo mensagem por mensagem
		
				 for (Update update : updates) {
					
					m = update.updateId()+1;
					
					//transforma a mensagem em uma string 
						msg  = update.message().text();
					
					}//fim do for
				}
				System.out.print(msg);
			return msg ;
	}//fim do metodo


//metodo para auxiliar o envio de mensagens faz a mesma coisa do metodo anterior mas nao transforma em string 
public Update up() {
	
	//inicia o objeto que vai guardar a mensagem 
	 Update update = null ;
	

		updatesResponse =  bot.execute(new GetUpdates().limit(1) );
		
		System.out.print(updatesResponse.isOk());
		if(updatesResponse.isOk() ) {
		//cria uma lista com tudo que foi capturado
		
		
		 List<Update> updates = updatesResponse.updates();
		 		
		 System.out.print(updates.size());
		 
		//vai percorrendo mensagem por mensagem

		 for (Update date : updates) {
			
		//m = update.updateId()+1;
			
			
				update  = date;
			
			}//fim do for
		}



return update ;
}//fim do metodo que envia o update


}
