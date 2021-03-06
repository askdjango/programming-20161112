package kr.festi.programming20161112;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class NaverWebtoonListActivity extends AppCompatActivity {

    ArrayList<Webtoon> webtoonList = new ArrayList<>();

    WebtoonArrayAdapter adapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("네이버 웹툰");
        setContentView(R.layout.activity_naver_webtoon_list);

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // progressDialog.setMessage("Loading ...");

        webtoonList.add(
            new Webtoon(
                "고수",
                "문정후",
                "http://comic.naver.com/webtoon/list.nhn?titleId=662774",
                "http://thumb.comic.naver.net/webtoon/662774/thumbnail/title_thumbnail_20151002175819_t125x101.jpg - not found"
            )
        );

        adapter = new WebtoonArrayAdapter(this, 0, webtoonList);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setEmptyView(findViewById(R.id.listViewEmptyView));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Webtoon webtoon = (Webtoon) adapterView.getAdapter().getItem(position);

                Intent intent = new Intent(view.getContext(), NaverWebtoonDetailActivity.class);
                intent.putExtra("webtoon", webtoon);   // TODO: Parcelable
                // intent.putExtra("webtoonUrl", webtoon.pageUrl);

                // intent.putExtra("title", webtoon.title);
                // intent.putExtra("author", webtoon.author);
                // intent.putExtra("pageUrl", webtoon.pageUrl);
                // intent.putExtra("profileImageUrl", webtoon.profileImageUrl);

                startActivity(intent);
            }
        });

        loadWebtoonList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.naver_webtoon_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if ( itemId == R.id.refreshMenu ) {
            loadWebtoonList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void loadWebtoonList() {
        String sourceUrl = "https://dl.dropboxusercontent.com/u/698019/askdjango/webtoon_list.json";

        progressDialog.show();

        Ion.with(this)
            .load(sourceUrl)
            .as(new TypeToken<List<Webtoon>>(){})
            .setCallback(new FutureCallback<List<Webtoon>>() {
                @Override
                public void onCompleted(Exception e, List<Webtoon> result) {
                    if ( e != null ) {
                        Toast.makeText(NaverWebtoonListActivity.this,
                            String.format("Exception : %s", e.toString()),
                            Toast.LENGTH_SHORT).show();
                    }
                    else if ( result != null ) {
                        webtoonList.clear();
                        webtoonList.addAll(result);
                        adapter.notifyDataSetChanged();
                    }
                    else {
                        Toast.makeText(NaverWebtoonListActivity.this, "result is null.", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
            });
    }

    class WebtoonArrayAdapter extends ArrayAdapter<Webtoon> {
        public WebtoonArrayAdapter(Context context, int resource, ArrayList<Webtoon> objects) {
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
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.network_connection_error)
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
}
