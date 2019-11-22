package exceptions;

public class ParserNotNautiljonException extends Exception
{

	private static final long serialVersionUID = 7980608946114427183L;

	public ParserNotNautiljonException(String message)
	{
		super(message);
	}
	
	public ParserNotNautiljonException()
	{
		super("Vous ne pouvez utiliser que le site de Nautiljon");
	}
}
