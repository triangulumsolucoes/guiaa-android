package triangulum.com.guiaa.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import triangulum.com.guiaa.R;
import triangulum.com.guiaa.model.Cidade;

/**
 * Created by triangulum on 29/12/14.
 */
public class CidadesAdapter extends BaseAdapter{

    private Activity activity;
    private ArrayList<Cidade> cidades;

    public  CidadesAdapter(Activity activity,ArrayList<Cidade> cidades){
     this.cidades = cidades;
     this.activity = activity;
    }

    @Override
    public int getCount() {
        return cidades.size();
    }

    @Override
    public Cidade getItem(int position) { return cidades.get(position); }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.row_cidades, null);

        TextView nomeCidade = (TextView)convertView.findViewById(R.id.nome);
        nomeCidade.setText(getItem(position).getNome());

        return convertView;
    }
}
