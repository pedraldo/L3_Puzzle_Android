package info.ups.fr.puzzlegame_template;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
        TextView textLvl;
        ImageView img;
        TextView textTime;
        View rowView;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = inflater.inflate(R.layout.list_level_row, parent, false);
        }else{
            rowView = convertView;
        }

        textLvl = (TextView) rowView.findViewById(R.id.text);
        img = (ImageView) rowView.findViewById(R.id.imgGauche);
        textTime = (TextView) rowView.findViewById(R.id.textTime);

        textLvl.setText(values[position].toString());

        if(values[position].isOk()){
            img.setImageResource(R.drawable.vvert);
            textTime.setText(this.getRefactorTime(values[position].getTime()));
            img.setVisibility(View.VISIBLE);
            textTime.setVisibility(View.VISIBLE);
        }else{
            if(position == 0 || values[position-1].isOk()){
                img.setVisibility(View.GONE);
                textTime.setVisibility(View.GONE);
            }else{
                img.setImageResource(R.drawable.cadenas);
                img.setVisibility(View.VISIBLE);
                textTime.setVisibility(View.GONE);
            }
        }

        rowView.setBackgroundResource(values[position].getBackgroundOnList());

        return rowView;
    }

    private String getRefactorTime(long time){
        time = time/1000;
        int minute = (int)((time%3600)/60);
        int seconde = (int)((time%3600)%60);

        String result = "";

        if(minute < 10)
            result += "0" + String.valueOf(minute) + ":";
        else
            result += String.valueOf(minute) + ":";
        if(seconde < 10)
            result += "0" + String.valueOf(seconde);
        else
            result += String.valueOf(seconde);

        return result;
    }
}
