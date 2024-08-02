package id.co.astra.polytechnic.prg6_remed_b;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DosenAddActivity extends Activity {

    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonSaveDosen;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_dosen);

        editTextName = findViewById(R.id.editTextName);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSaveDosen = findViewById(R.id.buttonSaveDosen);
        dbHelper = new DBHelper(this);

        buttonSaveDosen.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (!name.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                dbHelper.addDosen(name, username, password);
                Toast.makeText(DosenAddActivity.this, "Dosen ditambahkan", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(DosenAddActivity.this, "Harap isi semua kolom", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
