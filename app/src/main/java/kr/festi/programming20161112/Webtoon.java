package kr.festi.programming20161112;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;


public class Webtoon implements Parcelable {
    String title;
    String author;
    String pageUrl;
    String profileImageUrl;

    Webtoon(String title, String author, String pageUrl, String profileImageUrl) {
        this.title = title;
        this.author = author;
        this.pageUrl = pageUrl;
        this.profileImageUrl = profileImageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(this);
        parcel.writeString(jsonString);
        // parcel.writeString(title);
        // parcel.writeString(author);
        // parcel.writeString(pageUrl);
        // parcel.writeString(profileImageUrl);
    }

    public static final Creator<Webtoon> CREATOR = new Creator<Webtoon>() {
        @Override
        public Webtoon createFromParcel(Parcel in) {
            String jsonString = in.readString();
            Gson gson = new Gson();
            Webtoon webtoon = gson.fromJson(jsonString, Webtoon.class);
            return webtoon;

            // String title = in.readString();
            // String author = in.readString();
            // String pageUrl = in.readString();
            // String profileImageUrl = in.readString();
            // return new Webtoon(title, author, pageUrl, profileImageUrl);
        }

        @Override
        public Webtoon[] newArray(int size) {
            return new Webtoon[size];
        }
    };
}
