package com.example.appvet;

public class Animal {

    public int id;

    public String nome, SpTypeAnimal,idade;

    public Animal() {

    }

    public Animal(String nome, String SpTypeAnimal, String idade) {
        this.nome = nome;
        this.SpTypeAnimal = SpTypeAnimal;
        this.idade = idade;
    }

    @Override
    public String toString() {
        return nome + "  |  " + SpTypeAnimal + "  |  " + idade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSpTypeAnimal() {
        return SpTypeAnimal;
    }

    public void setSpTypeAnimal(String spTypeAnimal) {
        SpTypeAnimal = spTypeAnimal;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }
}
