package com.example.appvet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvAnimais;
    private ArrayAdapter adapter;
    private List<Animal> listaDeAnimal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        lvAnimais = findViewById(R.id.lvAnimais);

        carregarAnimal();


        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FormularioActivity.class);
                intent.putExtra("acao","inserir");
                startActivity(intent);
            }
        });

        lvAnimais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idAnimal = listaDeAnimal.get(position).getId();
                Intent intent = new Intent(MainActivity.this,FormularioActivity.class);
                intent.putExtra("acao","editar");
                intent.putExtra("idAnimal",idAnimal);
                startActivity(intent);
            }
        });

        lvAnimais.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int posicao, long id) {
                excluir(posicao);

                return true;
            }
        });

    }

    private void excluir(int posicao){
        Animal animal = listaDeAnimal.get(posicao);
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir...");
        alerta.setIcon(android.R.drawable.ic_input_delete);
        alerta.setMessage("Confirme a exclusão do Animal " + animal.getNome() + "?");
        alerta.setNeutralButton("Cancelar",null);

        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AnimalDAO.excluir(MainActivity.this,animal.getId());
                carregarAnimal();
            }
        });
        alerta.show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarAnimal();
    }

    private void carregarAnimal(){

        listaDeAnimal = AnimalDAO.getAnimais(this);

        if(listaDeAnimal.size() == 0) {
            Animal fake = new Animal("Lista vazia...","","");

            listaDeAnimal.add(fake);
            lvAnimais.setEnabled(false);
        }else{
            lvAnimais.setEnabled(true);
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaDeAnimal);
        lvAnimais.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}