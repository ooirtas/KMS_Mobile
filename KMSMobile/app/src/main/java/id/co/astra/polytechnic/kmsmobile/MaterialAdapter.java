package id.co.astra.polytechnic.kms_mobile;
// MaterialAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder> {

    private List<MaterialItem> materialList;

    public MaterialAdapter(List<MaterialItem> materialList) {
        this.materialList = materialList;
    }

    @NonNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_material_card, parent, false);
        return new MaterialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialViewHolder holder, int position) {
        MaterialItem item = materialList.get(position);
        holder.titleTextView.setText(item.getTitle());
        holder.programTextView.setText(item.getProgram());
        holder.authorTextView.setText(item.getAuthor());
        holder.dateTextView.setText(item.getDate());
        // You can set image resources here as well if needed
    }

    @Override
    public int getItemCount() {
        return materialList.size();
    }

    static class MaterialViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView programTextView;
        TextView authorTextView;
        TextView dateTextView;
        ImageButton iconImageView;
        ImageButton bookmarkImageView;

        public MaterialViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            programTextView = itemView.findViewById(R.id.programTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            iconImageView = itemView.findViewById(R.id.iconImageView);
        }
    }
}

