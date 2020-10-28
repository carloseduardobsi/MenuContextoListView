package br.example.menucontextolistview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener {

    private List<String> dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        String[] dadosVet = {"João", "Maria", "José"};
        dados = new ArrayList<String>(Arrays.asList(dadosVet));
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dados);

        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
        // Aqui registra-se o menu de contexto
        registerForContextMenu(getListView());
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contexto, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =

                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.menu_ver:
                String str = (String)getListView().getItemAtPosition(info.position);
                Toast.makeText(this, str, Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_remover:
                dados.remove(info.position);
                ((ArrayAdapter)getListView().getAdapter()).notifyDataSetChanged();
                break;
            default:
                super.onContextItemSelected(item);
        }
        return true;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, dados.get(i), Toast.LENGTH_LONG).show();
    }
}