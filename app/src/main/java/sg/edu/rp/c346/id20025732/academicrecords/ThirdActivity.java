package sg.edu.rp.c346.id20025732.academicrecords;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    TextView TvID,TvName,TvYear,TvResults;
    EditText EtID,Etname, Etyear,Etresults;
    Button btnupdate,btndelete,btncancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TvID = findViewById(R.id.textViewID);
        TvName = findViewById(R.id.RecordtextView);
        TvYear = findViewById(R.id.YeartextView);
        TvResults = findViewById(R.id.YeartextView);
        EtID = findViewById(R.id.editTextID);
        Etname = findViewById(R.id.editTextRecord);
        Etyear = findViewById(R.id.editTextYear);
        Etresults = findViewById(R.id.editTextGrade);
        btnupdate = findViewById(R.id.buttonShowupdate);
        btndelete = findViewById(R.id.buttonDelete);
        btncancel = findViewById(R.id.buttonCancel);

        Intent i = getIntent();
        final Records currentRecord = (Records) i.getSerializableExtra("Record");
        EtID.setText(currentRecord.getId() + "");
        Etname.setText(currentRecord.getName());
        Etyear.setText(currentRecord.getYear());
        Etresults.setText(currentRecord.getResults());

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentRecord.setName((Etname.getText().toString().trim()));
                currentRecord.setResults(Etresults.getText().toString().trim());
                int year = Integer.valueOf(Etyear.getText().toString().trim());
                currentRecord.setYear(year);

                int result = dbh.updateRecord(currentRecord);
                if (result < 0) {
                    Toast.makeText(ThirdActivity.this, "Record updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete " + Etname + "?");
                myBuilder.setCancelable(false);

                myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(ThirdActivity.this);
                        int result = dbh.deleteRecord(currentRecord.getId());
                        finish();
                    }
                });

                myBuilder.setPositiveButton("Cancel",null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes?");
                myBuilder.setCancelable(false);

                myBuilder.setNegativeButton("discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                myBuilder.setPositiveButton("Do not discard",null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });
    }
}