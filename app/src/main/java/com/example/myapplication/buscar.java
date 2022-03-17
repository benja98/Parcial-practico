package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class buscar extends AppCompatActivity {


    Button btnBuscar;
    EditText edtCorreo;
    String inicio="",correo="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        edtCorreo=findViewById(R.id.edtCorreo);
        btnBuscar=findViewById(R.id.btnBuscar);
        Bundle data=getIntent().getExtras();
        inicio =data.getString("inicio");

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo=edtCorreo.getText().toString().trim();
                if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
                    edtCorreo.setError("correo no valido");
                    edtCorreo.requestFocus();
                    return;
                }
                //validar si el correo existe
                login.consultaSql = "select * from usuarios where correo='"+correo+"';";
                Cursor usuario = login.base.rawQuery(login.consultaSql,null);
                if(usuario.getCount()==0){
                    Toast.makeText(getApplicationContext(),"Correo no encontrado!",Toast.LENGTH_LONG).show();
                    return;
                }

                String corr="";
                String nom="";
                String clav="";
                String tipo="";
                if (usuario.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                         corr=usuario.getString(0);
                         nom=usuario.getString(1);
                         clav=usuario.getString(2);
                         tipo=usuario.getString(3);
                    } while(usuario.moveToNext());
                }

                switch (inicio){
                    case "buscar":
                        Intent edit1 = new Intent(getApplicationContext(), datos.class);
                        edit1.putExtra("nombre",nom);
                        edit1.putExtra("correo",corr);
                        edit1.putExtra("clave",clav);
                        edit1.putExtra("tipo",tipo);
                        edit1.putExtra("inicio","buscar");
                        startActivityForResult(edit1, 5);
                        break;
                    case "editar":
                        Intent i = new Intent(getApplicationContext(), formulario.class);
                        i.putExtra("nombre",nom);
                        i.putExtra("correo",corr);
                        i.putExtra("clave",clav);
                        i.putExtra("tipo",tipo);
                        i.putExtra("confirmar",clav);
                        i.putExtra("inicio","editar");
                        startActivityForResult(i, 2);
                        break;
                    case "eliminar":
                        Intent edit3 = new Intent(getApplicationContext(), datos.class);
                        edit3.putExtra("nombre",nom);
                        edit3.putExtra("correo",corr);
                        edit3.putExtra("clave",clav);
                        edit3.putExtra("tipo",tipo);
                        edit3.putExtra("inicio","eliminar");
                        startActivityForResult(edit3, 5);
                        break;
                }
            }
        });
    }

    public void Cancelar(View v){
        finish();
    }


    public void Buscar(View v){
        correo=edtCorreo.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            edtCorreo.setError("correo no valido");
            edtCorreo.requestFocus();
            return;
        }
        //validar si el correo existe
        login.consultaSql = "select * from usuarios where correo='"+correo+"';";
        Cursor usuario = login.base.rawQuery(login.consultaSql,null);
        if(usuario.getCount()==0){
            Toast.makeText(getApplicationContext(),"Correo no encontrado!",Toast.LENGTH_LONG).show();
            return;
        }

        String nom=usuario.getString(1);
        String corr=usuario.getString(0);
        String clav=usuario.getString(2);
        String tipo=usuario.getString(3);

        switch (inicio){
            case "buscar":
                Intent edit1 = new Intent(getApplicationContext(), datos.class);
                edit1.putExtra("nombre",nom);
                edit1.putExtra("correo",corr);
                edit1.putExtra("clave",clav);
                edit1.putExtra("tipo",tipo);
                edit1.putExtra("inicio","buscar");
                startActivityForResult(edit1, 5);
                break;
            case "editar":
                Intent i = new Intent(getApplicationContext(), formulario.class);
                i.putExtra("nombre",nom);
                i.putExtra("correo",corr);
                i.putExtra("clave",clav);
                i.putExtra("tipo",tipo);
                i.putExtra("confirmar",clav);
                i.putExtra("inicio","editar");
                startActivityForResult(i, 2);
                break;
            case "eliminar":
                Intent edit3 = new Intent(getApplicationContext(), datos.class);
                edit3.putExtra("nombre",nom);
                edit3.putExtra("correo",corr);
                edit3.putExtra("clave",clav);
                edit3.putExtra("tipo",tipo);
                edit3.putExtra("inicio","eliminar");
                startActivityForResult(edit3, 5);
                break;
        }
    }
    @Override
    public void onActivityResult(int codigoActidad, int codigoResultado, Intent data){
        if(codigoActidad == 5 || codigoActidad==2){
            if(codigoResultado == RESULT_OK){
                finish();
            }
        }
    }
}
