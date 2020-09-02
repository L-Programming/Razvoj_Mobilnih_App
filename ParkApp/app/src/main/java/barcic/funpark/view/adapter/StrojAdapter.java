package barcic.funpark.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import barcic.funpark.R;
import barcic.funpark.model.Stroj;

public class StrojAdapter extends BaseAdapter {

    private List<Stroj> podaci;
    private StrojClickListener strojClickListener;
    private LayoutInflater layoutInflater;
    ViewHolder holder;
    Stroj stroj;


    public StrojAdapter(FragmentActivity activity, StrojClickListener strojClickListener) {
        this.strojClickListener = strojClickListener;
        layoutInflater = LayoutInflater.from(activity);
    }
    public void setPodaci(List<Stroj> strojevi) {

        this.podaci = strojevi;
    }

    @Override
    public int getCount() {
        return podaci == null ? 0:podaci.size();
    }

    @Override
    public Object getItem(int position) {
        return podaci.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        stroj =podaci.get(position);


        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.red_liste, null);
            holder = new ViewHolder (convertView);
            holder.strojName=convertView.findViewById(R.id.naziv_stroja);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.strojName.setText(stroj.getNazivStroja().toUpperCase());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strojClickListener.onItemClick(podaci.get(position));
            }
        });

        if (stroj.getPutanjaSlika() == null) {
            Picasso.get().load(R.drawable.placeholder).fit().centerCrop().into(holder.picture);
            return convertView;
        }
        Picasso.get().load(stroj.getPutanjaSlika()).fit().centerCrop().into(holder.picture);

        return convertView;
    }





    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView picture;
        private TextView strojName;


        ViewHolder(View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.slikaStroja);
            strojName = itemView.findViewById(R.id.naziv_stroja);

        }
    }







}

