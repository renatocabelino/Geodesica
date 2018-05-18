package br.edu.ifes.vitoria.geodesica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import br.edu.ifes.vitoria.geodesica.BibliotecaFuncoes;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    protected Spinner spinnerClasses;
    protected Spinner spinnerSubClasses;
    protected String classeFuncoes;
    protected EditText editAbiscissaAnterior;
    protected EditText editAbiscissaPosterior;
    protected EditText editOrdenadaAnterior;
    protected EditText editOrdenadaPosterior;
    protected Button buttonCalcular;
    protected DialogoResultado dialogoResultado = new DialogoResultado();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerClasses = (Spinner) findViewById(R.id.spinnerClasses);
        spinnerClasses.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterClasses = ArrayAdapter.createFromResource(this, R.array.classes_funcoes, android.R.layout.simple_spinner_item);
        adapterClasses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapterClasses to the spinner
        spinnerClasses.setAdapter(adapterClasses);

        //definindo o segundo spinner de subclasses para as funcoes
        spinnerSubClasses = (Spinner)findViewById(R.id.spinnerSubClasses);
        spinnerSubClasses.setOnItemSelectedListener(this);
        editAbiscissaAnterior = (EditText) findViewById(R.id.editAbiscissaAnterior);
        editAbiscissaPosterior = (EditText)findViewById(R.id.editAbiscissaPosterior);
        editOrdenadaAnterior = (EditText)findViewById(R.id.editOrdenadaAnterior);
        editOrdenadaPosterior = (EditText)findViewById(R.id.editOrdenadaPosterior);
        escondeParametros();

        //capturando o botao calcular
        buttonCalcular = (Button)findViewById(R.id.buttonCalcular);
        buttonCalcular.setOnClickListener(this);

        //instancia do dialogo para informar resultado de processamento

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinnerClasses) {
            classeFuncoes = (String) parent.getItemAtPosition(position);
            preencheSubclasse(classeFuncoes);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        escondeParametros();
    }
    public void preencheSubclasse (String classeSelecionada) {
        ArrayAdapter<CharSequence> adapterSubclasse;
        switch (classeSelecionada) {
            case "Topografia Básica":
                // Create an ArrayAdapter using the string array and a default spinner layout
                adapterSubclasse = ArrayAdapter.createFromResource(this,
                        R.array.subclasses_funcoes_topografia_basica, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapterSubclasse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinnerSubClasses.setAdapter(adapterSubclasse);
                exibeParametros();
                break;
             default:
                 adapterSubclasse = ArrayAdapter.createFromResource(this,
                         R.array.subclasses_funcoes_trigonometria, android.R.layout.simple_spinner_item);
                 // Specify the layout to use when the list of choices appears
                 adapterSubclasse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                 // Apply the adapter to the spinner
                 spinnerSubClasses.setAdapter(adapterSubclasse);
                 escondeParametros();
                 break;
        }
    }
    public void escondeParametros () {
        editOrdenadaPosterior.setVisibility(View.INVISIBLE);
        editOrdenadaAnterior.setVisibility(View.INVISIBLE);
        editAbiscissaPosterior.setVisibility(View.INVISIBLE);
        editAbiscissaAnterior.setVisibility(View.INVISIBLE);
    }
    public void exibeParametros () {
        editOrdenadaPosterior.setVisibility(View.VISIBLE);
        editOrdenadaAnterior.setVisibility(View.VISIBLE);
        editAbiscissaPosterior.setVisibility(View.VISIBLE);
        editAbiscissaAnterior.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCalcular:
                String[] result = String.valueOf(editAbiscissaAnterior.getText()).split(":");
                float abiscissaAnterior = Float.parseFloat(result[1]);
                result = String.valueOf(editAbiscissaPosterior.getText()).split(":");
                float abiscissaPosterior = Float.parseFloat(result[1]);
                result = String.valueOf(editOrdenadaAnterior.getText()).split(":");
                float ordenadaAnterior = Float.parseFloat(result[1]);
                result = String.valueOf(editOrdenadaPosterior.getText()).split(":");
                float ordenadaPosterior = Float.parseFloat(result[1]);
                float azimute = BibliotecaFuncoes.Azimute(abiscissaAnterior, ordenadaAnterior, abiscissaPosterior, ordenadaPosterior);
                Log.i("buttonCalcular", String.valueOf(azimute));
                Bundle bundle = new Bundle();
                bundle.putFloat("azimute", azimute);
                dialogoResultado.setArguments(bundle);
                dialogoResultado.show(getFragmentManager(), "123");
                break;
        }
    }

}
