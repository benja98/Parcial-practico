package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class datos extends AppCompatActivity {

    TextView tvNombre,tvCorreo,tvClave,tvTipo;
    String nombre,correo,clave,tipo,inicio="",inicio2="";
    Button btnConfirmar,btnCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        Bundle datos=getIntent().getExtras();
        nombre=datos.getString("nombre","");
        correo=datos.getString("correo","");
        clave=datos.getString("clave","");
        tipo=datos.getString("tipo","");
        inicio=datos.getString("inicio","");
        inicio2=datos.getString("inicio2","");

        btnCancelar=findViewById(R.id.btnCancelar);
        btnConfirmar=findViewById(R.id.btnConfirmar);

        tvNombre=findViewById(R.id.tvNombre);
        tvCorreo=findViewById(R.id.tvCorreo);
        tvClave=findViewById(R.id.tvClave);
        tvTipo=findViewById(R.id.tvTipo);

        tvNombre.setText(tvNombre.getText().toString().trim()+nombre);
        tvCorreo.setText(tvCorreo.getText().toString().trim()+correo);
        tvClave.setText(tvClave.getText().toString().trim()+clave);
        tvTipo.setText(tvTipo.getText().toString().trim()+tipo);

        if (inicio.equals("buscar")){
            btnConfirmar.setText("Regresar");
            btnCancelar.setText("ir al menu");
        }
        if (inicio.equals("eliminar")){
            btnConfirmar.setText("regresar");
            btnCancelar.setText("Eliminar");
        }

    }

    public void confirmar(View v){
        if (inicio.equals("buscar")){
            finish();
            return;
        }
        if (inicio.equals("eliminar")){
            finish();
            return;
        }

        if (inicio2.equals("editar") || inicio2.equals("listado")){
            //Actualizar un registro
            login.base.execSQL("UPDATE Usuarios SET clave='"+clave+"',nombre='"+nombre+"',tipo='"+tipo+"' WHERE correo='"+correo+"' ");
            Toast.makeText(getApplicationContext(),"LOS DATOS SE EDITARON",Toast.LENGTH_LONG).show();
            Intent i=new Intent();
            setResult(RESULT_OK,i);
            finish();
            return;
        }

        login.base.execSQL("insert into usuarios values('"+correo+"','"+nombre+"','"+clave+"','"+tipo+"')");
//        login.objUsuarios.setCorreo(correo);
//        login.objUsuarios.setClave(clave);
//        login.objUsuarios.setNombre(nombre);
//        login.objUsuarios.setNivel(tipo);
        Toast.makeText(getApplicationContext(),"LOS DATOS SE ALMACENARON",Toast.LENGTH_LONG).show();
        Intent i=new Intent();
        setResult(RESULT_OK,i);
        finish();
    }

    public void cancelar(View v){
        if (inicio.equals("buscar")){
            Intent i=new Intent();
            setResult(RESULT_OK,i);
            finish();
            return;
        }
        if (inicio.equals("eliminar")){
            login.base.execSQL("DELETE FROM usuarios WHERE correo='"+correo+"'");
            Toast.makeText(getApplicationContext(),"SE ELIMINO EL REGISTRO",Toast.LENGTH_LONG).show();
            Intent i=new Intent();
            setResult(RESULT_OK,i);
            finish();
            return;
        }
        if (inicio.equals("formulario")){
            finish();
            return;
        }
        Intent i=new Intent();
        setResult(RESULT_OK,i);
        finish();
    }
}
