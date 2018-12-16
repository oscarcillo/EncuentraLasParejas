package com.otr.encuentralasparejas;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    RadioButton modoNormal;
    RadioButton modoUnaOportunidad;
    RadioButton modoTiempoLimitado;
    TextView descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //iniciar variables
        descripcion = findViewById(R.id.descripcion);
        modoNormal = findViewById(R.id.modoNormal);
        modoUnaOportunidad = findViewById(R.id.modoUnaOportunidad);
        modoTiempoLimitado = findViewById(R.id.modoTiempoLimitado);

        //
        seleccionarModo(null);
        //
        int modo = getIntent().getIntExtra("modo", 1);
        if(modo==1)
            modoNormal.setChecked(true);
        else if(modo == 2) {
            modoUnaOportunidad.setChecked(true);
        }
        else if(modo == 3)
            modoTiempoLimitado.setChecked(true);

    }

    public void seleccionarModo(View v){
        if(modoNormal.isChecked())
            descripcion.setText(getText(R.string.descripcion_normal));
        else if(modoUnaOportunidad.isChecked())
            descripcion.setText(getText(R.string.descripcion_unaoportunidad));
        else if(modoTiempoLimitado.isChecked())
            descripcion.setText(getText(R.string.descripcion_tiempolimitado));
    }

    public void jugar(View v){
        Intent i = new Intent(this, MainActivity.class);
        if(modoNormal.isChecked())
            i.putExtra("modo", 1);
        else if(modoUnaOportunidad.isChecked())
            i.putExtra("modo", 2);
        else if(modoTiempoLimitado.isChecked())
            i.putExtra("modo", 3);
        startActivity(i);
        finish();
    }
}
