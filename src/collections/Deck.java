package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import models.Card;

/**
 * A deck for containing a list of cards
 * 
 * @author Ryder James
 */
public class Deck implements Iterable<Card> {
	private final ArrayList<Card> my_cards;

	/**
	 * Initializes a new deck with any number of cards
	 * 
	 * @param initialCards - the cards to initially add to the deck, if any
	 */
	public Deck(Card... initialCards) {
		my_cards = new ArrayList<>();
		for (Card c : initialCards) {
			my_cards.add(c);
		}
	}

	/**
	 * Adds a card to the deck
	 * 
	 * @param toAdd
	 */
	public void add(Card toAdd) {
		my_cards.add(toAdd);
	}

	/**
	 * Adds any number of cards to the deck
	 * 
	 * @param cardsToAdd
	 */
	public void add(Card... cardsToAdd) {
		for (Card card : cardsToAdd) {
			add(card);
		}
	}

	/**
	 * Removes a specific card from the deck
	 * 
	 * @param toRemove
	 */
	public void remove(Card toRemove) {
		my_cards.remove(toRemove);
	}

	/**
	 * Removes any number of specific cards from the deck
	 * 
	 * @param cardsToRemove
	 */
	public void remove(Card... cardsToRemove) {
		for (Card card : cardsToRemove) {
			remove(card);
		}
	}

	/**
	 * Draws a card off the "top" (the highest index) of the deck
	 * 
	 * @return the drawn card
	 */
	public Card draw() {
		Card result = my_cards.get(my_cards.size() - 1);
		my_cards.remove(result);
		return result;
	}

	/**
	 * Draws a certain number of cards off the "top" (the highest index) of the deck
	 * 
	 * @param numberToDraw - the number of cards to draw
	 * 
	 * @return a {@code Deck} containing all the drawn cards
	 */
	public Deck draw(int numberToDraw) {
		if (numberToDraw < 1) {
			throw new IllegalArgumentException("Must draw at least 1 card!");
		} else if (numberToDraw > my_cards.size()) {
			throw new IllegalArgumentException("Not enough cards in the deck!");
		}

		Deck deck = new Deck();

		for (int i = 0; i < numberToDraw; i++) {
			deck.add(this.draw());
		}

		return deck;
	}

	/**
	 * @return true if the number of cards in this deck is 0
	 */
	public boolean isEmpty() {
		return (my_cards.size() == 0);
	}

	/**
	 * Shuffles the deck. More specifically, switches the card at every index for a card at a random
	 * index.
	 */
	public void shuffle() {
		for (int i = 0; i < my_cards.size(); i++) {
			Card temp = my_cards.get(i);
			int randIndex = new Random().nextInt(my_cards.size());

			my_cards.set(i, my_cards.get(randIndex));
			my_cards.set(randIndex, temp);
		}
	}

	@Override
	public String toString() {
		return Arrays.toString(my_cards.toArray(new Card[0]));
	}

	@Override
	public Iterator<Card> iterator() {
		return my_cards.iterator();
	}
}
