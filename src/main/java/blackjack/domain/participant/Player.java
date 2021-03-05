package blackjack.domain.participant;

import static blackjack.domain.participant.Dealer.THRESHOLD;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public class Player extends Participant {

    public Player(String name, Hand cardHand) {
        super(name, cardHand);
    }

    public static Player from(String name) {
        return new Player(name, Hand.createEmptyHand());
    }

    public void receiveCard(Card card) {
        cardHand.add(card);
    }

    @Override
    public boolean isBust() {
        return cardHand.sumAceToOne() > THRESHOLD;
    }

    @Override
    public int sumCard() {
        // todo ACE 두장 => 12 반환 필요 (현재는 2반환)
        int resultByOne = cardHand.sumAceToOne();
        int resultByEleven = cardHand.sumAceToEleven();

        // 21에 딱 맞음
        if (resultByOne == THRESHOLD) {
            return resultByOne;
        }
        if (resultByEleven == THRESHOLD) {
            return resultByEleven;
        }

        // 둘다 21을 넘지 않는다면 큰 수 리턴
        if (resultByEleven < THRESHOLD && resultByOne < THRESHOLD) {
            return Math.max(resultByEleven, resultByOne);
        }

        // 하나 or 둘이 21 넘음
        return Math.min(resultByEleven, resultByOne);
    }
}
