package events;

import java.io.IOException;

import exceptions.ParserNotNautiljonException;
import main.MuchachoMain;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import utils.web.Parser;

public class TestWeb extends ListenerAdapter
{

	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		if(event.getMessage().getContentRaw().startsWith(MuchachoMain.prefix)) {
			if(event.getMessage().getContentRaw().contains("test")) {
				try
				{
					System.out.println("test");
					Parser parser = new Parser("https://www.nautiljon.com/animes/arifureta+shokugyou+de+sekai+saikyou.html");
				}
				catch (IOException | ParserNotNautiljonException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
