package com.google.notes_7;

public interface CardsSource {
    CardData getCardData(int position);
    int size();
}
