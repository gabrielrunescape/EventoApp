package br.com.gabrielrunescape.eventoapp;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;

/**
 * Created by gabriel on 02/10/16.
 */

public class ItemVideoAdapter extends ArrayAdapter<ItemVideo> {
    public ItemVideoAdapter(Context context, List<ItemVideo> lista) {
        super(context, 0, lista);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;

        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_video, parent, false);
        }

        ItemVideo itemVideo = getItem(position);

        TextView titulo = (TextView) itemView.findViewById(R.id.Titulo);
        TextView data = (TextView) itemView.findViewById(R.id.Data);

        titulo.setText(itemVideo.getTitulo());
        data.setText(itemVideo.getData());

        return itemView;
    }
}
