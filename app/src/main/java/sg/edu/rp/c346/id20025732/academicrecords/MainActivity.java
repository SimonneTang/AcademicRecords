package sg.edu.rp.c346.id20025732.academicrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvname, tvyear, tvgrade;
    EditText etname, etyear,etgrade;
    Button btnshowlist, btninsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getTitle().toString() + " ~ " + "Insert Record");

        tvname = findViewById(R.id.RecordtextView);
        tvyear = findViewById(R.id.YeartextView);
        tvgrade = findViewById(R.id.textViewGrade);
        etname = findViewById(R.id.editTextRecord);
        etyear = findViewById(R.id.editTextYear);
        etgrade = findViewById(R.id.editTextGrade);
        btnshowlist = findViewById(R.id.buttonShowlist);
        btninsert = findViewById(R.id.buttonInsert);

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                String name = etname.getText().toString().trim();
                String year = etyear.getText().toString().trim();
                int year_str = Integer.valueOf(year);
                String results = etgrade.getText().toString().trim();
                dbh.insertrecords(name,year_str,results);
                dbh.close();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                etname.setText("");
                etyear.setText("");
                etgrade.setText("");
                return;
            }
        });

        btnshowlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }
}