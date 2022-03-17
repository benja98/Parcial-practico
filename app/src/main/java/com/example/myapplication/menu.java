package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void Nuevo(View v){
        Intent i = new Intent(getApplicationContext(), formulario.class);
        i.putExtra("nombre","");
        i.putExtra("correo","");
        i.putExtra("clave","");
        i.putExtra("confirmar","");
        i.putExtra("inicio","menu");
        startActivityForResult(i, 2);
    }
    public void Salir(View v){
        finish();
    }

    public void Buscar(View v){
        Intent find = new Intent(getApplicationContext(), buscar.class);
        find.putExtra("inicio", "buscar");
        startActivityForResult(find, 3);
    }

    public void Editar(View v){
        Intent i = new Intent(getApplicationContext(), buscar.class);
        i.putExtra("inicio","editar");
        startActivityForResult(i, 3);
    }

    public void Eliminar(View v){
        Intent del = new Intent(getApplicationContext(), buscar.class);
        del.putExtra("inicio", "eliminar");
        startActivityForResult(del, 3);
    }

    public void Listado(View v){
        Intent list = new Intent(getApplicationContext(), listado.class);
        startActivityForResult(list, 4);
    }
}
