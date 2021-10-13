package com.example.appvet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private Spinner spTypeAnimal;
    private EditText etIdade;
    private Button btnCadastro;
    private String acao;
    private Animal animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        spTypeAnimal = findViewById(R.id.spTypeAnimal);
        etIdade = findViewById(R.id.etIdade);
        btnCadastro = findViewById(R.id.btnCadastro);

        acao = getIntent().getStringExtra("acao");

        if(acao.equals("editar")){
            carregarFormulario();
        }

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                cadastrar();
            }
        });
    }

    private void carregarFormulario(){
        int id = getIntent().getIntExtra("idAnimal",0);
        animal = AnimalDAO.getAnimalById(this,id);
        etNome.setText(animal.getNome());

        String[] TipoAnimal = getResources().getStringArray(R.array.TypeAnimal);
        for(int i = 1; i < TipoAnimal.length; i++) {
            if(animal.getSpTypeAnimal().equals(TipoAnimal[i])){
                spTypeAnimal.setSelection(i);
            }
        }
    }

    private void cadastrar(){

        String nome = etNome.getText().toString();
        String idade = etIdade.getText().toString();

        if(nome.isEmpty() || spTypeAnimal.getSelectedItemPosition() == 0 || idade.isEmpty()){
            Toast.makeText(this,"Voce deve preencher todos os campos!",Toast.LENGTH_LONG).show();

        }
        else{
            if(acao.equals("inserir")){
                animal = new Animal();

            }

            animal.setNome(nome);
            animal.setSpTypeAnimal(spTypeAnimal.getSelectedItem().toString());
            animal.setIdade(idade);

            if(acao.equals("inserir")){
                AnimalDAO.inserir(this,animal);
                etNome.setText("");
                spTypeAnimal.setSelection(0,true);
                etIdade.setText("");
            }else{
                AnimalDAO.editar(this,animal);
                finish();
            }
        }
    }
}