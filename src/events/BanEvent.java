package events;

import main.MuchachoMain;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BanEvent extends ListenerAdapter
{

	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		if(event.getMessage().getContentRaw().startsWith(MuchachoMain.prefix)) {
			if(event.getMessage().getContentRaw().contains("ban")) {
				event.getChannel().sendMessage("Ban de <@518169452253151243> dans 30 secondes").queue();;
			}
		}
	}
}
