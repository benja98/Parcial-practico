package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import backend.ConexionSqlite;
import backend.Usuarios;

public class login extends AppCompatActivity {
    public static SQLiteDatabase base;

    public static String consultaSql;

    EditText edtUsuario, edtClave;
    String user="a@a.a",pass="admin",usuario="",clave="";
    public static Usuarios objUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ConexionSqlite objConexoin = new ConexionSqlite(getApplicationContext(),"sistemas",null,1);
        Log.i("ANDROID","SE CREO LA CONEXION");
        base = objConexoin.getWritableDatabase();
        Log.i("ANDROID","SE CREO LA BASE... ");

        consultaSql = "select * from usuarios";
        Cursor usuarios = base.rawQuery(consultaSql,null);
        if(usuarios.getCount()==0){//si no hay ningun usuario registrado que registre este que seria quemado. esto para poder logearse
            base.execSQL("insert into usuarios values('benjamin@gmail.com','Benjamin','123','Usuario')");
        }

        objUsuarios = new Usuarios();

        edtUsuario = findViewById(R.id.edtUsuario);
        edtClave = findViewById(R.id.edtClave);


    }
    public void ingresar(View v){

        usuario=edtUsuario.getText().toString().trim();
        clave=edtClave.getText().toString().trim();

        if (usuario.equals("")){
            edtUsuario.setError("Valor requerido");
            edtUsuario.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(usuario).matches()){
            edtUsuario.setError("Correo no valido");
            edtUsuario.requestFocus();
            return;
        }
        if (clave.equals("")){
            edtClave.setError("Valor requerido");
            edtClave.requestFocus();
            return;
        }
//        if (!(usuario.equals(user) && clave.equals(pass))){
//            Toast.makeText(getApplicationContext(),"Datos incorrectos",Toast.LENGTH_LONG).show();
//            return;
//        }

        //validar si el correo existe
        consultaSql = "select * from usuarios where correo='"+usuario+"' and clave='"+clave+"'";
        Cursor usuarioEncontrados = base.rawQuery(consultaSql,null);
        if(usuarioEncontrados.getCount()>0){//si se encontro registros es por que si existe el usuario y que lo deje ingresar al menu
            edtUsuario.setText("");
            edtClave.setText("");
            Intent i = new Intent(getApplicationContext(), menu.class);
            startActivityForResult(i, 1);
        }else{
            Toast.makeText(getApplicationContext(),"Datos incorrectos",Toast.LENGTH_LONG).show();
            return;
        }
    }
}
