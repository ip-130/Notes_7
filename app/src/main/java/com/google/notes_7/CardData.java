package com.google.notes_7;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class CardData {
    public String title;       // заголовок
    public String description; // описание
    public int picture;        // изображение
    public boolean like;       // флажок
    public Date date;          // дата

    public CardData(String title, String description, int picture, boolean like, Date date){
        this.title = title;
        this.description=description;
        this.picture=picture;
        this.like=like;
        this.date=date;
    }

    protected CardData(Parcel in) {
        title = in.readString();
        description = in.readString();
        picture = in.readInt();
        like = in.readByte() != 0;
        date = new Date(in.readLong());
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(picture);
        dest.writeByte((byte) (like ? 1 : 0));
        dest.writeLong(date.getTime());
    }

    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<CardData> CREATOR = new Parcelable.Creator<CardData>() {
        @Override
        public CardData createFromParcel(Parcel in) {
            return new CardData(in);
        }

        @Override
        public CardData[] newArray(int size) {
            return new CardData[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPicture() {
        return picture;
    }

    public boolean isLike() {
        return like;
    }

    public Date getDate() {
        return date;
    }
}
