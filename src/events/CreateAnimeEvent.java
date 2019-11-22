package events;

import java.io.IOException;
import java.time.LocalDate;

import exceptions.ParserNotNautiljonException;
import main.MuchachoMain;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import utils.Anime;
import utils.web.Parser;

public class CreateAnimeEvent extends ListenerAdapter
{

	// &anime add <link>
	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		if(event.getMessage().getContentRaw().startsWith(MuchachoMain.prefix)) {
			if(event.getMessage().getContentRaw().contains("anime add")) {
				String[] args = event.getMessage().getContentRaw().split(" ");
				if(args.length >= 3) {
					try
					{
						Parser parser = new Parser(args[2]);
						Anime anime = new Anime(parser.getOriginalTitle(), parser.getAlternativeTitle(), parser.getImageURL(), args[2], parser.getNumberOfEpisode(), 1, LocalDate.now());
						event.getChannel().sendMessage("Anime ajoute !").queue();
						event.getChannel().sendMessage(anime.createEmbed().build()).queue();
					}
					catch (IOException | ParserNotNautiljonException e)
					{
						event.getChannel().sendMessage("Lien invalide").queue();
					}
				}
				else
					event.getChannel().sendMessage("Argument insuffisant, &anime add <link>").queue();
			}
		}
	}
}
