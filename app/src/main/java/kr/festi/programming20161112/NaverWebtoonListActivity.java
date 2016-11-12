package kr.festi.programming20161112;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NaverWebtoonListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("네이버 웹툰");
        setContentView(R.layout.activity_naver_webtoon_list);

        Webtoon[] webtoonList = {
            new Webtoon("고수", "문정후", "http://thumb.comic.naver.net/webtoon/662774/thumbnail/title_thumbnail_20151002175819_t125x101.jpg"),
            new Webtoon("마음의 소리", "조석", "http://thumb.comic.naver.net/webtoon/662774/thumbnail/title_thumbnail_20151002175819_t125x101.jpg"),
            new Webtoon("노블레스", "손재호/이광수", "http://thumb.comic.naver.net/webtoon/25455/thumbnail/title_thumbnail_20100614120245_t125x101.jpg"),
            new Webtoon("신의탑", "SIU", "http://thumb.comic.naver.net/webtoon/183559/thumbnail/title_thumbnail_20160516123017_t125x101.jpg")
        };
        final WebtoonArrayAdapter adapter = new WebtoonArrayAdapter(this, 0, webtoonList);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Webtoon webtoon = (Webtoon) adapterView.getAdapter().getItem(position);

                Intent intent = new Intent(view.getContext(), NaverWebtoonDetailActivity.class);
                // intent.putExtra("webtoon", webtoon);   // TODO: Parcelable
                intent.putExtra("webtoonUrl", "http://m.naver.com");
                startActivity(intent);
            }
        });
    }

    class WebtoonArrayAdapter extends ArrayAdapter<Webtoon> {
        public WebtoonArrayAdapter(Context context, int resource, Webtoon[] objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if ( convertView == null ) {
                convertView = getLayoutInflater().inflate(R.layout.row_webtoon, parent, false);
            }

            Webtoon webtoon = (Webtoon) getItem(position);

            ImageView profileImageView = (ImageView) convertView.findViewById(R.id.profileImageView);
            // profileImageView.setImageBitmap();
            Glide
                .with(convertView.getContext())
                .load(webtoon.profileImageUrl)
                .centerCrop()
                // .placeholder(R.drawable.loading_spinner)
                .crossFade()
                .into(profileImageView);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
            TextView authorTextView = (TextView) convertView.findViewById(R.id.authorTextView);

            titleTextView.setText(webtoon.title);
            authorTextView.setText(webtoon.author);

            return convertView;
        }
    }

    class Webtoon {
        String title;
        String author;
        String profileImageUrl;

        Webtoon(String title, String author, String profileImageUrl) {
            this.title = title;
            this.author = author;
            this.profileImageUrl = profileImageUrl;
        }
    }
}
