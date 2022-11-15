package com.example.crud_bd2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText rutE, nomE, apeE;
    ListView ListViewEstudiante;
    Button addE, modE, remE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rutE = findViewById(R.id.RutEstudiante);
        nomE = findViewById(R.id.NombreEstudiante);
        apeE = findViewById(R.id.ApellidoEstudiante);
        ListViewEstudiante = findViewById(R.id.InfoEstudiantes);
        addE = findViewById(R.id.RegistrarEstudiante);
        modE = findViewById(R.id.ModificarEstudiante);
        remE = findViewById(R.id.EliminarEstudiante);

        addE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String r = rutE.getText().toString();
                String n = nomE.getText().toString();
                String a = apeE.getText().toString();

                if (r.isEmpty() && n.isEmpty() && a.isEmpty())
                    Toast.makeText(getApplicationContext(), "Llene Los campos", Toast.LENGTH_SHORT).show();
            }else{

            }
        });
    }


}