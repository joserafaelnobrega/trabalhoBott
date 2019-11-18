import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
public class Enviarmsg {
	TelegramBot BOT = TelegramBotAdapter.build("1041357533:AAEZF5cos0qU6MDm5-LTlLLtuOGETGwGuGk");
	//objeto responsável por gerenciar o envio de respostas
	private 	SendResponse sendResponse;
	//objeto responsável por gerenciar o envio de ações do chat
	private	BaseResponse baseResponse;

		
		// recebe uma mensagem e responde o objeto update que foi capturado na tela 
		public void montarMsg(Update update, String msg) {
			
			if(update != null) {
				//mensagem de digitando
			baseResponse = BOT.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
			//envia uma mensagem 
			sendResponse = BOT.execute(new SendMessage(update.message().chat().id(),  msg ));
				update = null;
			}
		}//fim do metodo

}//fim da classe
