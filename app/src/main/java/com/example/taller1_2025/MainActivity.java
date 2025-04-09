package com.example.taller1_2025;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    TextView mensajeTextView;
    EditText mensajeEditText;
    private int tamaño = 8;

    public void editarMensaje(View view) {
        mensajeEditText = findViewById(R.id.mensajeEditText);
        mensajeTextView = findViewById(R.id.mensajeTextView);
        String mensajeString = mensajeEditText.getText().toString();
        mensajeTextView.setText(mensajeString);
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mensajeTextView = findViewById(R.id.mensajeTextView);
        mensajeTextView.setText("Hola Mundo");

    }

    public void Incrementa(View view) {
        if (tamaño < 56) {
            tamaño = tamaño + 8;
            if (tamaño > 56) {
                tamaño = 56;
            }
            mensajeTextView.setTextSize(tamaño);
        }
    }

    public void Disminuir(View view) {
        if (tamaño > 8) {
            tamaño = tamaño - 8;
            if (tamaño < 8) {
                tamaño = 8;
            }
            mensajeTextView.setTextSize(tamaño);
        }
    }

    public void cambiarFuente(View view) {

        final String[] fuentes = {"Sans Serif", "Serif", "Monospace", "Roboto", "Arial"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una fuente");
        builder.setItems(fuentes, (dialog, which) -> {
            switch (which) {
                case 0:
                    mensajeTextView.setTypeface(Typeface.SANS_SERIF);
                    break;
                case 1:
                    mensajeTextView.setTypeface(Typeface.SERIF);
                    break;
                case 2:
                    mensajeTextView.setTypeface(Typeface.MONOSPACE);
                    break;
                case 3:
                    mensajeTextView.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
                    break;
                case 4:
                    mensajeTextView.setTypeface(Typeface.create("serif", Typeface.NORMAL));
                    break;
                default:
                    Toast.makeText(MainActivity.this, "Fuente no seleccionada", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    public void cambiarColor(View view) {
        // Crear un diálogo personalizado
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona un color para el texto");

        // Inflar el diseño del diálogo personalizado
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_color_picker, null);
        builder.setView(dialogView);

        // Obtener referencias a los campos de entrada RGB
        EditText redInput = dialogView.findViewById(R.id.redInput);
        EditText greenInput = dialogView.findViewById(R.id.greenInput);
        EditText blueInput = dialogView.findViewById(R.id.blueInput);

        // Configurar el botón de aceptar
        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            try {
                int red = Integer.parseInt(redInput.getText().toString());
                int green = Integer.parseInt(greenInput.getText().toString());
                int blue = Integer.parseInt(blueInput.getText().toString());

                // Validar los valores RGB
                if (red >= 0 && red <= 255 && green >= 0 && green <= 255 && blue >= 0 && blue <= 255) {
                    int color = Color.rgb(red, green, blue);
                    mensajeTextView.setTextColor(color);
                } else {
                    Toast.makeText(MainActivity.this, "Valores RGB inválidos", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Por favor ingresa valores válidos", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        // Mostrar el diálogo
        builder.show();
    }



}
