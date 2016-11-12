package kr.festi.programming20161112;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class NaverWebtoonListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("네이버 웹툰");
        setContentView(R.layout.activity_naver_webtoon_list);

        Webtoon[] webtoonList = {
            new Webtoon("고수", "문정후"),
            new Webtoon("용비불패", "문정후"),
            new Webtoon("노블레스", "손재호/이광수"),
            new Webtoon("신의탑", "SIU")
        };
        WebtoonArrayAdapter adapter = new WebtoonArrayAdapter(this, 0, webtoonList);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
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

        Webtoon(String title, String author) {
            this.title = title;
            this.author = author;
        }
    }
}
