package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class listado extends AppCompatActivity {

    ListView lvListado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        //obtener el objeto lvListado del xml
        lvListado = findViewById(R.id.lvListado);

        //definir el arreglo de valores a mostrar en el lvListado

        login.consultaSql = "select * from usuarios";
        Cursor usuarios = login.base.rawQuery(login.consultaSql,null);

        System.out.println("cantidad de correos"+ " "+usuarios.getCount());
        String [] items = new String[usuarios.getCount()];
        if (usuarios.moveToFirst()){
            for (int i =0 ;i<usuarios.getCount();i++){
                items[i]=usuarios.getString(0);
                usuarios.moveToNext();
            }
        }

        // define un nuevo adaptador
        // primer parámetro - contexto
        // segundo parámetro - diseño para la fila
        // tercer parámetro - id del textView en el que se escriben los datos
        // cuarto parametro - la matriz de datos
        ArrayAdapter adaptador= new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,items);

        //asignar adaptador a listView
        lvListado.setAdapter(adaptador);

        //evento del click para el listView
        lvListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado=String.valueOf(lvListado.getItemAtPosition(position));
                login.consultaSql = "select * from usuarios where correo='"+seleccionado+"';";
                Cursor usuario = login.base.rawQuery(login.consultaSql,null);
                String cor="";
                String nom="";
                String clav="";
                String tipo="";
                if (usuario.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        cor=usuario.getString(0);
                        nom=usuario.getString(1);
                        clav=usuario.getString(2);
                        tipo=usuario.getString(3);
                    } while(usuario.moveToNext());
                }

                Intent i = new Intent(getApplicationContext(),formulario.class);
                i.putExtra("nombre",nom);
                i.putExtra("correo",cor);
                i.putExtra("clave",clav);
                i.putExtra("confirmar",clav);
                i.putExtra("tipo",tipo);
                i.putExtra("inicio","listado");

                startActivityForResult(i,2);
            }
        });

    }
    @Override
    public void onActivityResult(int codigoActidad, int codigoResultado, Intent data){
        if(codigoActidad == 2){
            if(codigoResultado == RESULT_OK){
                finish();
            }
        }
    }
}
