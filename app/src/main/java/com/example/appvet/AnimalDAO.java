package com.example.appvet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    public static void inserir(Context context, Animal animal) {
        ContentValues values = new ContentValues();
        values.put("nome", animal.getNome());
        values.put("SpTypeAnimal", animal.getSpTypeAnimal());
        values.put("idade", animal.getIdade());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.insert("animal", null, values);
    }

    public static void editar(Context context, Animal animal) {
        ContentValues values = new ContentValues();
        values.put("nome", animal.getNome());
        values.put("SpTypeAnimal", animal.getSpTypeAnimal());
        values.put("idade", animal.getIdade());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.update("animal", values, " id = " + animal.getId(), null);
    }


    public static void excluir(Context context, int idAnimal) {

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("animal", " id = " + idAnimal, null);
    }

    public static List<Animal> getAnimais(Context context){
        List<Animal> lista = new ArrayList<>();

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM animal order by nome", null);

        if(cursor.getCount() > 0) {

            cursor.moveToFirst();

            do{
                Animal Animal = new Animal();
                Animal.setId(cursor.getInt(0));
                Animal.setNome(cursor.getString(1));
                Animal.setSpTypeAnimal(cursor.getString(2));
                Animal.setIdade(cursor.getString(3));

                lista.add(Animal);

            }while(cursor.moveToNext());

        }

        return  lista;
    }

    public static Animal getAnimalById(Context context, int idAnimal) {

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM animal where id = " + idAnimal, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            Animal Animal = new Animal();
            Animal.setId(cursor.getInt(0));
            Animal.setNome(cursor.getString(1));
            Animal.setSpTypeAnimal(cursor.getString(2));
            Animal.setIdade(cursor.getString(3));


            return Animal;
        } else {
            return null;
        }

    }
}
