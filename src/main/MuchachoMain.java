package main;

import javax.security.auth.login.LoginException;

import events.BanEvent;
import events.PingEvent;
import events.TestWeb;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class MuchachoMain {

	public static JDA jda;
	public static String prefix = "&";
	
	public static void main(String[] args) throws LoginException {
		jda = new JDABuilder(AccountType.BOT).setToken("").build();
		jda.getPresence().setActivity(Activity.playing("se faire coder"));
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		
		jda.addEventListener(new PingEvent());
		jda.addEventListener(new TestWeb());
		jda.addEventListener(new BanEvent());
	}
}
