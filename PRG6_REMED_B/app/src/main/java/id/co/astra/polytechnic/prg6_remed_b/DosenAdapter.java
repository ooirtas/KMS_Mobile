package id.co.astra.polytechnic.prg6_remed_b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class DosenAdapter extends ArrayAdapter<Dosen> {

    private Context context;
    private List<Dosen> dosenList;

    public DosenAdapter(Context context, List<Dosen> dosenList) {
        super(context, 0, dosenList);
        this.context = context;
        this.dosenList = dosenList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_dosen, parent, false);
        }

        // Get the current dosen item
        Dosen dosen = dosenList.get(position);

        // Find and set up views
        TextView textViewName = convertView.findViewById(R.id.textViewName);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);

        textViewName.setText(dosen.getName());

        // Set up delete button functionality
        buttonDelete.setOnClickListener(v -> {
            // Handle delete action
            deleteDosen(dosen.getId());
        });

        return convertView;
    }

    private void deleteDosen(long id) {
        // Implement the logic to delete dosen
        // This should ideally be handled in the activity or a ViewModel,
        // but for the purpose of this adapter, we will notify the activity to handle deletion
        if (context instanceof DosenActivity) {
            ((DosenActivity) context).showDeleteConfirmationDialog(id);
        }
    }
}

