package id.co.astra.polytechnic.prg6_remed_b;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DosenActivity extends AppCompatActivity {

    private ListView listViewDosen;
    private Button buttonAddDosen;
    private DosenAdapter dosenAdapter;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_dosen);

        listViewDosen = findViewById(R.id.listViewDosen);
        buttonAddDosen = findViewById(R.id.buttonAddDosen);
        dbHelper = new DBHelper(this);

        // Initialize adapter and set it to ListView
        updateDosenList();

        buttonAddDosen.setOnClickListener(v -> {
            Intent intent = new Intent(DosenActivity.this, DosenAddActivity.class);
            startActivity(intent);
        });

        listViewDosen.setOnItemClickListener((parent, view, position, id) -> {
            // Handle item click if needed
        });

        listViewDosen.setOnItemLongClickListener((parent, view, position, id) -> {
            showDeleteConfirmationDialog(id);
            return true;
        });
    }

    private void updateDosenList() {
        List<Dosen> dosenList = dbHelper.getAllDosen();
        dosenAdapter = new DosenAdapter(this, dosenList);
        listViewDosen.setAdapter(dosenAdapter);
    }

    void showDeleteConfirmationDialog(long id) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Data")
                .setMessage("Apakah Anda yakin ingin menghapus data dosen ini?")
                .setPositiveButton("Hapus", (dialog, which) -> {
                    dbHelper.deleteDosen(id);
                    updateDosenList();
                    Toast.makeText(DosenActivity.this, "Data dosen dihapus", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Batal", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            updateDosenList();
        }
    }
}
