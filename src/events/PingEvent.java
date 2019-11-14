package events;

import main.MuchachoMain;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PingEvent extends ListenerAdapter
{

	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		if(event.getMessage().getContentRaw().startsWith(MuchachoMain.prefix)) {
			if(event.getMessage().getContentRaw().endsWith("ping")) {
				String message = "Ta gueule " + event.getAuthor().getAsMention() + " je bosse";
				event.getChannel().sendMessage(message).queue();
				
				for(Member m : event.getGuild().getMembers())
					System.out.println(m.getUser().getAsMention() + " : " + m.getUser().getAsTag());
			}
		}
	}
}
