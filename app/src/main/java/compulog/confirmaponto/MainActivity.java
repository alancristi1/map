package compulog.confirmaponto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import persistence.Database;

public class MainActivity extends AppCompatActivity {

    EditText inputCodigo;
    EditText inputNome;
    EditText inputEndereco;
    Button btnSalvar;
    TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ponto);

        inputCodigo = findViewById(R.id.inputCodigo);
        inputNome = findViewById(R.id.inputNome);
        btnSalvar = findViewById(R.id.btnSalvar);
        txtInfo = findViewById(R.id.txtInfo);
        inputEndereco = findViewById(R.id.inputEndereco);
        inputCodigo.setVisibility(View.GONE);
        txtInfo.setText(R.string.MainTxtInfoNovo);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nome = inputNome.getText().toString();
                final String endereco = inputEndereco.getText().toString();

                if(nome.isEmpty()){
                    inputNome.setError("Preencha o campo Nome!");
                    inputNome.requestFocus();
                }else if(!nome.isEmpty()){
                    inputNome.setError(null);
                }

                if(endereco.isEmpty()){
                    inputEndereco.setError("Preencha o campo Nome!");
                    inputEndereco.requestFocus();
                }else if(!endereco.isEmpty()){
                    inputEndereco.setError(null);
                }
                if(!endereco.isEmpty() && !nome.isEmpty()){
                    validarNovoPonto();
                }
            }
        });
    }

    public void validarNovoPonto(){
        String nome = inputNome.getText().toString();
        String endereco = inputEndereco.getText().toString();
        Database database = new Database(getBaseContext());
        if(nome.isEmpty() || nome.length() == 0){
            inputNome.setError("Preencha o campo Nome!");
            inputNome.requestFocus();
        }if(endereco.isEmpty() || endereco.length() == 0){
            inputEndereco.setError("Preencha o campo Endereço!");
        }else{
            database.gravarDados("" ,nome, endereco);
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("ativar", true);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}

