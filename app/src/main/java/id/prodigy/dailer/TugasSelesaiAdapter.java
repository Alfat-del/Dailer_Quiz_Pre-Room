package id.prodigy.dailer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.BreakIterator;
import java.util.ArrayList;

public class TugasSelesaiAdapter extends RecyclerView.Adapter<TugasSelesaiAdapter.TugasSelesaiViewHolder> {
    private ArrayList<Tugas> listTugasSelesai;

    public TugasSelesaiAdapter(ArrayList<Tugas> listTugasSelesai) { this.listTugasSelesai = listTugasSelesai; }

    @NonNull
    @Override
    public TugasSelesaiAdapter.TugasSelesaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_tugas_selesai, parent, false);
        TugasSelesaiViewHolder vHolder = new TugasSelesaiViewHolder(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TugasSelesaiAdapter.TugasSelesaiViewHolder holder, int position) {
        holder.tvPelajaran.setText(normalizeString(listTugasSelesai.get(position).getPelajaran(), 33));
        holder.tvTopik.setText(listTugasSelesai.get(position).getTopik());
        holder.tvKategori.setText(listTugasSelesai.get(position).getKategoriTugas());
        holder.tvDeadline.setText(listTugasSelesai.get(position).getWaktuDeadline() + " | "+ listTugasSelesai.get(position).getTanggalDeadline());
        holder.tvCatatan.setText("'" + listTugasSelesai.get(position).getCatatan() + "'");
        boolean isExpandable = listTugasSelesai.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
        if (isExpandable) {
            holder.ivDropdown.setRotation(90);
        } else {
            holder.ivDropdown.setRotation(0);
        }
    }

    @Override
    public int getItemCount() {
        return (listTugasSelesai == null) ? 0 : listTugasSelesai.size();
    }

    private String normalizeString(String s, int maxLength) {
        return (s.length() >= maxLength) ? (s.substring(0, maxLength) + "...") : s;
    }

    public class TugasSelesaiViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout expandableLayout;
        private TextView tvPelajaran, tvTopik, tvKategori, tvCatatan, tvDeadline;
        private ImageView ivDropdown, ivUncheckTugasSelesai, ivDeleteTugasSelesai;

        public TugasSelesaiViewHolder(@NonNull View view) {
            super(view);
            initcomponents(view);



            ivDropdown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Tugas tugasSelesai = listTugasSelesai.get(getAdapterPosition());
                    tugasSelesai.setExpandable(!tugasSelesai.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            ivDeleteTugasSelesai.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    final AlertDialog diaglogEditTugas = new AlertDialog.Builder(view.getContext())
                            .setTitle("Hapus Tugas")
                            .setView(R.layout.dialog_delete_tugas)
                            .create();
                    diaglogEditTugas.show();


                    Button btnTidakHapusTugas = diaglogEditTugas.findViewById(R.id.btn_hapustidaktugas);
                    Button btnYaHapusTugas = diaglogEditTugas.findViewById(R.id.btn_hapusyatugas);

                    btnTidakHapusTugas.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            diaglogEditTugas.dismiss();
                            Tugas tugas = listTugasSelesai.get(getAdapterPosition());
                            tugas.setExpandable(!tugas.isExpandable());
                            notifyItemChanged(getAdapterPosition());
                        }
                    });

                    btnYaHapusTugas.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Hapus tugas
                            diaglogEditTugas.dismiss();
                        }
                    });
                }
            });


        }

        private void initcomponents(View view) {
            tvPelajaran = view.findViewById(R.id.tv_tugasselesaipelajaran);
            tvKategori = view.findViewById(R.id.tv_tugasselesaikategori);
            tvTopik = view.findViewById(R.id.tv_tugasselesaitopik);
            tvDeadline = view.findViewById(R.id.tv_tugasselesaideadline);
            tvCatatan = view.findViewById(R.id.tv_tugasselesaicatatan);

            expandableLayout = view.findViewById(R.id.rl_tugasselesaiexpandablecontent);
            ivDropdown = view.findViewById(R.id.iv_tugasselesaidropdown);
            ivUncheckTugasSelesai = view.findViewById(R.id.iv_tugasselesaicheck);
            ivDeleteTugasSelesai = view.findViewById(R.id.iv_tugasselesaidelete);
        }
    }
}
