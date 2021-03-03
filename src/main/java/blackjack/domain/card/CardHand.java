package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardHand {

    private final List<Card> cards;

    public CardHand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public boolean isBurst() {
        return cards.stream()
                .mapToInt(Card::getRankValue)
                .sum() > 21;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
