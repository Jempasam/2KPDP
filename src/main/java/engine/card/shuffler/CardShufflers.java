package engine.card.shuffler;

/**
 * 
 * @author Samuel Demont
 *
 */
public class CardShufflers {
	public static ICardShuffler FAST=new FastCardShuffler();
	public static ICardShuffler NONE=new NoCardShuffler();
}
