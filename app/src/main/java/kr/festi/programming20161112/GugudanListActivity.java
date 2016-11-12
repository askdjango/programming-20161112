package kr.festi.programming20161112;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class GugudanListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gugudan_list);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String name = (String) adapterView.getAdapter().getItem(position);

                StringBuffer resultString = new StringBuffer();
                int number = (position+2);
                for(int i=1; i<=9; i++) {
                    int result = number * i;
                    resultString.append(String.format("%d * %d = %d\n", number, i, result));
                }

                String message = String.format("%s\n\n%s", name, resultString.toString());
                Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
