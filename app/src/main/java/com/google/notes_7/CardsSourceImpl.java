package com.google.notes_7;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CardsSourceImpl implements CardsSource {
    private List<CardData> dataSource;
    private Resources resources;    // ресурсы приложения

    public CardsSourceImpl(Resources resources) {
        dataSource = new ArrayList<>(5);
        this.resources = resources;
    }

    public CardsSource init(CardsSourceResponse cardsSourceResponse){
        // строки заголовков из ресурсов
        String[] titles = resources.getStringArray(R.array.titles);
        // строки описаний из ресурсов
        String[] descriptions = resources.getStringArray(R.array.descriptions);
        // изображения
        int[] pictures = getImageArray();
        // заполнение источника данных
        for (int i = 0; i < descriptions.length; i++) {
            dataSource.add(new CardData(titles[i], descriptions[i], pictures[i], false, Calendar.getInstance().getTime()));
        }

        if (cardsSourceResponse != null){
            cardsSourceResponse.initialized(this);
        }

        return this;
    }

    // Механизм вытаскивания идентификаторов картинок
    // https://stackoverflow.com/questions/5347107/creating-integer-array-of-resource-ids
    private int[] getImageArray(){
        TypedArray pictures = resources.obtainTypedArray(R.array.pictures);
        int length = pictures.length();
        int[] answer = new int[length];
        for(int i = 0; i < length; i++){
            answer[i] = pictures.getResourceId(i, 0);
        }
        return answer;
    }

    public CardData getCardData(int position) {
        return dataSource.get(position);
    }

    public int size(){
        return dataSource.size();
    }

    @Override
    public void deleteCardData(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateCardData(int position, CardData cardData) {
        dataSource.set(position, cardData);
    }

    @Override
    public void addCardData(CardData cardData) {
        dataSource.add(cardData);
    }

    @Override
    public void clearCardData() {
        dataSource.clear();
    }
}