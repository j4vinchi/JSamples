package mx.caltec.jsamples.adapters;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mx.caltec.jsamples.R;
import mx.caltec.jsamples.models.Contacto;

/**
 * Created by jcruz on 03/05/16.
 */
public class ContactoAdapter extends BaseAdapter {
    List<Contacto> contactos = new ArrayList<>();
    Activity activity;


    public ContactoAdapter (Activity activity, List<Contacto> contactos) {
        this.activity = activity;
        this.contactos = contactos;

    }
    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Object getItem(int position) {
        return contactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Contacto contacto = contactos.get(position);
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(activity).inflate(R.layout.contacto_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.nombreContacto.setText(contacto.getNombre());
        if (contacto.getTelefono() != null) {
            holder.numeroContacto.setText(contacto.getTelefono());
        } else {
            holder.numeroContacto.setText("Sin número de telefono");
        }

        holder.emailContacto.setText(contacto.getEmail());
        if (contacto.getPhoto() != null) {
            holder.ivContacto.setImageURI(contacto.getPhoto());
        } else {
            holder.ivContacto.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.contact));
        }

        return view;
    }

    static class ViewHolder {

        @InjectView(R.id.ivContacto)
        ImageView ivContacto;
        @InjectView(R.id.nombreContacto)
        TextView nombreContacto;
        @InjectView(R.id.numeroContacto)
        TextView numeroContacto;
        @InjectView(R.id.emailContacto)
        TextView emailContacto;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
