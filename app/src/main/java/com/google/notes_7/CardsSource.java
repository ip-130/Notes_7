package com.google.notes_7;

public interface CardsSource {
    CardsSource init(CardsSourceResponse cardsSourceResponse);
    CardData getCardData(int position);
    int size();
    void deleteCardData(int position);
    void updateCardData(int position, CardData cardData);
    void addCardData(CardData cardData);
    void clearCardData();
}
