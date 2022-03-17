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
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class formulario extends AppCompatActivity {

    EditText edtNombre,edtCorreo,edtClave,edtConfirmar;
    RadioGroup rdbG;
    RadioButton rdbUsuario,rdbAsistente,rdbAdministrador;
    Button btnGuardar,btnCancelar;

    String nombre="",correo="",clave="",confirmar="",tipo="",inicio="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        edtNombre=findViewById(R.id.edtNombre);
        edtCorreo=findViewById(R.id.edtCorreo);
        edtClave=findViewById(R.id.edtClave);
        edtConfirmar=findViewById(R.id.edtConfirmar);

        rdbG=findViewById(R.id.rdbG);

        rdbUsuario=findViewById(R.id.rdbUsuario);
        rdbAsistente=findViewById(R.id.rdbAsistente);
        rdbAdministrador=findViewById(R.id.rdbAdministrador);

        Bundle datos = getIntent().getExtras();

        nombre=datos.getString("nombre","");
        correo=datos.getString("correo","");
        clave=datos.getString("clave","");
        confirmar=datos.getString("confirmar","");
        tipo=datos.getString("tipo","");
        inicio=datos.getString("inicio","");

        edtNombre.setText(nombre);
        if (inicio.equals("editar") || inicio.equals("listado")){
            edtCorreo.setKeyListener(null);
        }
        edtCorreo.setText(correo);
        edtClave.setText(clave);
        edtConfirmar.setText(confirmar);
        if (tipo.equals("Usuario")){
            rdbUsuario.setChecked(true);
        }
        if (tipo.equals("Asistente")){
            rdbAsistente.setChecked(true);
        }
        if (tipo.equals("Administrador")){
            rdbAdministrador.setChecked(true);
        }

    }

    public void Guardar(View v){
        nombre=edtNombre.getText().toString().trim();
        correo=edtCorreo.getText().toString().trim();
        clave=edtClave.getText().toString().trim();
        confirmar=edtConfirmar.getText().toString().trim();

        if (TextUtils.isEmpty(nombre)){
            edtNombre.setError("valor requerido");
            edtNombre.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(correo)){
            edtCorreo.setError("valor requerido");
            edtCorreo.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            edtCorreo.setError("correo no valido");
            edtCorreo.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(clave)){
            edtClave.setError("valor requerido");
            edtClave.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(confirmar)){
            edtConfirmar.setError("valor requerido");
            edtConfirmar.requestFocus();
            return;
        }
        if(!clave.equals(confirmar)){
            edtConfirmar.setError("las claves no coinciden");
            edtConfirmar.requestFocus();
            return;
        }
        login.consultaSql = "select * from usuarios where correo='"+correo+"'";
        Cursor usuarios = login.base.rawQuery(login.consultaSql,null);
        int indiceCorreo = login.objUsuarios.getIndiceCorreo(correo);
        if(usuarios.getCount()>0 && !inicio.equals("editar") && !inicio.equals("listado")){
            edtCorreo.setError("El correo ya existe");
            edtCorreo.requestFocus();
            return;
        }

        RadioButton r;
        r=findViewById(rdbG.getCheckedRadioButtonId());  //id de radio button seleccionado
        tipo=r.getText().toString().trim();

        Intent i = new Intent(getApplicationContext(), datos.class);

        i.putExtra("nombre",nombre);
        i.putExtra("correo",correo);
        i.putExtra("clave",clave);
        i.putExtra("tipo",tipo);
        i.putExtra("inicio","formulario");
        i.putExtra("inicio2",inicio);

        startActivityForResult(i, 5);
    }

    public void cancelar(View v){

        Intent i =new Intent();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onActivityResult(int codigoActidad, int codigoResultado, Intent data){
        if(codigoActidad == 5){
            if(codigoResultado == RESULT_OK){
                Intent i =new Intent();
                setResult(RESULT_OK);
                finish();
            }
        }
    }
}
