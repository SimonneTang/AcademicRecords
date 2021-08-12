package sg.edu.rp.c346.id20025732.academicrecords;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Records> al;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        setTitle(getTitle().toString() + " ~ " + "Insert Records");
        lv = findViewById(R.id.lv);

        DBHelper dbh = new DBHelper(this);
        al = dbh.getAllRecords();
        dbh.close();

        adapter = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("Records", al.get(position));
                startActivity(i);
            }


        });
    }
}
