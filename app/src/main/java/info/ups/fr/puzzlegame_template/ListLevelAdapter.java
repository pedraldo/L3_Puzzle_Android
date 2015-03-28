package info.ups.fr.puzzlegame_template;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Arnaud on 28/03/2015.
 */
public class ListLevelAdapter extends ArrayAdapter<Level> {

    private Context context;
    private Level[] values;

    public ListLevelAdapter(Context context, int resource, Level[] values) {
        super(context, resource, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        View rowView;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = inflater.inflate(R.layout.list_level_row, parent, false);
        }else{
            rowView = convertView;
        }

        textView = (TextView) rowView.findViewById(R.id.text);
        textView.setText(values[position].toString());

        rowView.setBackgroundResource(values[position].getBackgroundOnList());

        return rowView;
    }
}
