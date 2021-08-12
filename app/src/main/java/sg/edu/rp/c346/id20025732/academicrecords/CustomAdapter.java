package sg.edu.rp.c346.id20025732.academicrecords;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Records> Recordlist;

    public CustomAdapter(@NonNull Context context, int resource,
                         ArrayList<Records> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        Recordlist = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);
        TextView tvname = rowView.findViewById(R.id.textViewRName);
        TextView tvYear = rowView.findViewById(R.id.textViewRYear);
        TextView tvGrade = rowView.findViewById(R.id.textViewRGrade);

        Records currentVersion = Recordlist.get(position);
        tvname.setText(currentVersion.getName());
        tvYear.setText(currentVersion.getYear() + "");
        tvGrade.setText(currentVersion.getResults());


        return rowView;
    }

}
