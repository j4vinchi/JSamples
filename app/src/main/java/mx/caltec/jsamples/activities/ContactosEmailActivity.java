package mx.caltec.jsamples.activities;

import android.app.ActionBar;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mx.caltec.jsamples.R;
import mx.caltec.jsamples.adapters.ContactoAdapter;
import mx.caltec.jsamples.models.Contacto;

public class ContactosEmailActivity extends AppCompatActivity {
    private static final String TAG = ContactosEmailActivity.class.getSimpleName();
    private ListView lista;
    private List<Contacto> elementos = new ArrayList<>();
    private ContactoAdapter adapter;
    private Map<String, Integer> mapIndex;


    /*
     * Codigo para recuperar los contactos almacenados en el telefono
     * filtrando solo contactos que tengan email
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUI();
    }

    private void setUI() {
        setContentView(R.layout.activity_contactos_email);
        lista = (ListView) findViewById(R.id.lista);
        elementos = getNameEmailDetails();

        Collections.sort(elementos, new Comparator<Contacto>() {
            public int compare(Contacto c1, Contacto c2) {
                return c1.getNombre().toUpperCase().compareToIgnoreCase(c2.getNombre().toLowerCase());
            }
        });

        adapter = new ContactoAdapter(this, elementos);
        lista.setAdapter(adapter);
        getIndexList(elementos);
        displayIndex();
    }

    public List<Contacto> getNameEmailDetails(){
        List<Contacto> contactos = new ArrayList<Contacto>();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor cur1 = cr.query(
                        ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                        new String[]{id}, null);
                while (cur1.moveToNext()) {
                    String name = cur1.getString(cur1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    Log.e("Name :", name);
                    String email = null;
                    if (cur1.getString(cur1.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)) != null) email = cur1.getString(cur1.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                    Log.e("Email", email);
                    Uri uri = null;
                    if (cur1.getString(cur1.getColumnIndex(ContactsContract.Contacts.PHOTO_URI)) != null) uri = Uri.parse(cur1.getString(cur1.getColumnIndex(ContactsContract.Contacts.PHOTO_URI)));

                    String telefono = null;

                    /*
                    String id2 = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    Cursor cur2 = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id2}, null);
                    while (cur2.moveToNext()) {
                        if (cur2.getString(cur1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)) != null) telefono = cur2.getString(cur2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                    cur2.close();
                    */

                    if (cur1.getString(cur1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)) != null) telefono = cur1.getString(cur1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Contacto contacto = new Contacto(uri, name, email, telefono);
                    contactos.add(contacto);
                }
                cur1.close();
            }
        }
        cur.close();
        return contactos;
    }

    private void getIndexList(List<Contacto> contactos) {
        Log.d(TAG, "elemntos size " + contactos.size());
        mapIndex = new LinkedHashMap<>();
        for (int i = 0; i < contactos.size(); i++) {
            Contacto contacto = contactos.get(i);
            String index = contacto.getNombre().toUpperCase().substring(0, 1);
            if (mapIndex.get(index) == null) {
                mapIndex.put(index, i);
            }
        }
        Log.d(TAG, "mapIndex size " + mapIndex.size());
    }

    private void displayIndex() {
        LinearLayout indexLayout = (LinearLayout) findViewById(R.id.sideIndex);
        List<String> indexList = new ArrayList<>(mapIndex.keySet());
        for (String index : indexList) {
            final TextView textView;
            textView = (TextView) getLayoutInflater().inflate(R.layout.side_index_item, null);
            textView.setText(index);
            textView.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f));
            textView.setTextColor(ContextCompat.getColor(this, R.color.blanco));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lista.setSelection(mapIndex.get(textView.getText()));
                }
            });
            indexLayout.addView(textView);
        }
    }

}