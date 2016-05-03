package mx.caltec.jsamples.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mx.caltec.jsamples.R;

public class ListViewIndexActivity extends AppCompatActivity {
    private static final String TAG = ListViewIndexActivity.class.getSimpleName();
    private Map<String, Integer> mapIndex;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUI();
    }

    private void setUI() {
        setContentView(R.layout.activity_list_view_index);
        String[] elementos = getResources().getStringArray(R.array.fruits_array);
        Arrays.asList(elementos);
        lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, elementos));
        getIndexList(elementos);
        displayIndex();
    }

    private void getIndexList(String[] elementos) {
        Log.d(TAG, "elemntos size " + elementos.length);
        mapIndex = new LinkedHashMap<>();
        for (int i = 0; i < elementos.length; i++) {
            String elemento = elementos[i];
            String index = elemento.substring(0,1);
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
            textView.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
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
