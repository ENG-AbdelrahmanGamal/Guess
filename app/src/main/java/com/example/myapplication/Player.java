package com.example.myapplication;


import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
    private String name;
    private int attempts;

    public Player(String name, int attempts) {
        this.name = name;
        this.attempts = attempts;
    }

    protected Player(Parcel in) {
        name = in.readString();
        attempts = in.readInt();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getAttempts() {
        return attempts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(attempts);
    }
}
