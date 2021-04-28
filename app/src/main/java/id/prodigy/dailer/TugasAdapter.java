package id.prodigy.dailer;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TugasAdapter extends RecyclerView.Adapter<TugasAdapter.TugasViewHolder> {
    private ArrayList<Tugas> listTugas;
    private String keteranganDeadline;

    public TugasAdapter(ArrayList<Tugas> listTugas) {
        this.listTugas = listTugas;
    }

    @NonNull
    @Override
    public TugasAdapter.TugasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_tugas, parent, false);
        TugasViewHolder vHolder = new TugasViewHolder(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TugasAdapter.TugasViewHolder holder, int position) {
        try {
            keteranganDeadline = getKeteranganDeadline(listTugas.get(position).getTanggalDeadline());
            if (position - 1 >= 0) {
                String sDate1 = getKeteranganDeadline(listTugas.get(position-1).getTanggalDeadline());
                String sDate2 = getKeteranganDeadline(listTugas.get(position).getTanggalDeadline());
                if (!sDate1.equals(sDate2)) {
                    holder.tvSpace.setHeight(80);
                    holder.tvKeteranganDeadline.setHeight(55);
                    holder.tvKeteranganDeadline.setTextSize(15);
                    holder.tvKeteranganDeadline.setText(keteranganDeadline);

                } else {
                    holder.tvSpace.setHeight(0);
                    holder.tvKeteranganDeadline.setHeight(0);
                    holder.tvKeteranganDeadline.setTextSize(0);
                    holder.tvKeteranganDeadline.setText("");
                }
            } else {
                holder.tvSpace.setHeight(20);
                holder.tvKeteranganDeadline.setHeight(55);
                holder.tvKeteranganDeadline.setTextSize(15);
                keteranganDeadline = getKeteranganDeadline(listTugas.get(position).getTanggalDeadline());

                holder.tvKeteranganDeadline.setText(keteranganDeadline);
            }
            System.out.println(position + " | " + keteranganDeadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.tvPelajaran.setText(normalizeString(listTugas.get(position).getPelajaran(), 100));
        holder.tvTopik.setText(listTugas.get(position).getTopik());
        holder.tvKategori.setText(listTugas.get(position).getKategoriTugas());
        holder.tvDeadline.setText(listTugas.get(position).getWaktuDeadline() + " | "+ listTugas.get(position).getTanggalDeadline());
        holder.tvCatatan.setText("'" + listTugas.get(position).getCatatan() + "'");
        boolean isExpandable = listTugas.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
        if (isExpandable) {
            holder.ivDropdown.setRotation(90);
        } else {
            holder.ivDropdown.setRotation(0);
        }



    }

    @Override
    public int getItemCount() {
        return (listTugas == null) ? 0 : listTugas.size();
    }

    private String normalizeString(String s, int maxLength) {
        return (s.length() >= maxLength) ? (s.substring(0, maxLength) + "...") : s;
    }

    private String getKeteranganDeadline(String tanggalHolderSekarang) throws ParseException {
        Date dateHariIni = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2021");
        Date dateSekarang = new SimpleDateFormat("dd-MM-yyyy").parse(tanggalHolderSekarang);
        long selisihWaktu = dateSekarang.getTime() - dateHariIni.getTime();
        int selisihHari = (int) (selisihWaktu / (1000 * 60 * 60 * 24));
        switch (selisihHari) {
            case 0 : {
                return "Hari ini";
            }
            case 1 : {
                return "Besok";
            }
            case 2 : {
                return "Lusa";
            }
            default: {
                if (selisihHari >= 3 && selisihHari <= 6) {
                    return "Minggu Ini";
                } else if (selisihHari >= 7 && selisihHari <= 13) {
                    return "Minggu Depan";
                } else if (selisihHari >= 14 && selisihHari <= 20) {
                    return "3 Minggu Lagi";
                } else {
                    return "Dicicil aja ya..";
                }
            }
        }
    }


    public class TugasViewHolder extends RecyclerView.ViewHolder{
        private TextView tvPelajaran, tvTopik, tvKategori, tvCatatan, tvDeadline, tvKeteranganDeadline;
        private TextView tvSpace;
        private ImageView ivDropdown, ivEditTugas, ivDoneTugas, ivDeleteTugas;

        private LinearLayout linearLayout;
        private RelativeLayout expandableLayout;

        public TugasViewHolder(@NonNull View view) {
            super(view);
            tvPelajaran = view.findViewById(R.id.tv_pelajaran);
            tvKategori = view.findViewById(R.id.tv_kategori);
            tvTopik = view.findViewById(R.id.tv_topik);

            tvDeadline = view.findViewById(R.id.tv_deadline);
            tvKeteranganDeadline = view.findViewById(R.id.tv_keterangandeadline);
            tvSpace = view.findViewById(R.id.tv_space);

            tvCatatan = view.findViewById(R.id.tv_catatan);
            linearLayout = view.findViewById(R.id.ll_expand);
            expandableLayout = view.findViewById(R.id.rl_expandablecontent);
            ivDropdown = view.findViewById(R.id.iv_dropdown);

            ivEditTugas = view.findViewById(R.id.iv_note);
            ivDoneTugas = view.findViewById(R.id.iv_check);
            ivDeleteTugas = view.findViewById(R.id.iv_delete);

            ivDropdown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Tugas tugas = listTugas.get(getAdapterPosition());
                    tugas.setExpandable(!tugas.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });


            // Edit tugas
            ivEditTugas.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    final AlertDialog diaglogEditTugas = new AlertDialog.Builder(view.getContext())
                            .setTitle("Edit Tugas")
                            .setView(R.layout.dialog_edit_tugas)
                            .create();
                    diaglogEditTugas.show();

                    EditText etPelajaranLama = diaglogEditTugas.findViewById(R.id.et_editpelajaran);
                    EditText etTopikLama = diaglogEditTugas.findViewById(R.id.et_edittopik);
                    EditText etWaktuDeadlineLama = diaglogEditTugas.findViewById(R.id.et_editwaktudeadline);
                    EditText etTanggalDeadlineLama = diaglogEditTugas.findViewById(R.id.et_edittanggaldeadline);
                    EditText etCatatanLama = diaglogEditTugas.findViewById(R.id.et_editcatatan);

                    // Set and get spinner (kategori tugas)
                    String[] kategoriTugas = {"Tugas Individu", "Tugas Kelompok"};
                    Spinner spin = diaglogEditTugas.findViewById(R.id.spinner_editkategoritugas);
                    ArrayAdapter aa = new ArrayAdapter((Context) view.getContext(), android.R.layout.simple_spinner_item, kategoriTugas);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin.setAdapter(aa);
                    int IndeksKategoriTugasDipilih = (listTugas.get(getAdapterPosition()).getKategoriTugas().equals("Tugas Individu")) ? 0 : 1;
                    spin.setSelection(IndeksKategoriTugasDipilih);
                    final String[] kategoriTugasBaru = {""};
                    spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            kategoriTugasBaru[0] = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                    etPelajaranLama.setText(listTugas.get(getAdapterPosition()).getPelajaran());
                    etTopikLama.setText(listTugas.get(getAdapterPosition()).getTopik());
                    etWaktuDeadlineLama.setText(listTugas.get(getAdapterPosition()).getWaktuDeadline());
                    etTanggalDeadlineLama.setText(listTugas.get(getAdapterPosition()).getTanggalDeadline());
                    etCatatanLama.setText(listTugas.get(getAdapterPosition()).getCatatan());




                    // Kembali dari edit tugas tanpa mengubah apapun
                    Button dialogKembali = diaglogEditTugas.findViewById(R.id.btn_editkembali);
                    dialogKembali.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            diaglogEditTugas.dismiss();
                            Tugas tugas = listTugas.get(getAdapterPosition());
                            tugas.setExpandable(!tugas.isExpandable());
                            notifyItemChanged(getAdapterPosition());
                        }
                    });


                    // Simpan tugas yang telah di edit
                    Button dialogSimpan = diaglogEditTugas.findViewById(R.id.btn_editsimpan);
                    dialogSimpan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EditText etPelajaranBaru = diaglogEditTugas.findViewById(R.id.et_editpelajaran);
                            EditText etTopikBaru = diaglogEditTugas.findViewById(R.id.et_edittopik);
                            EditText etWaktuDeadlineBaru = diaglogEditTugas.findViewById(R.id.et_editwaktudeadline);
                            EditText etTanggalDeadlineBaru = diaglogEditTugas.findViewById(R.id.et_edittanggaldeadline);
                            EditText etCatatanBaru = diaglogEditTugas.findViewById(R.id.et_editcatatan);

                            listTugas.get(getAdapterPosition()).setPelajaran(etPelajaranBaru.getText().toString());
                            listTugas.get(getAdapterPosition()).setTopik(etTopikBaru.getText().toString());
                            listTugas.get(getAdapterPosition()).setWaktuDeadline(etWaktuDeadlineBaru.getText().toString());
                            listTugas.get(getAdapterPosition()).setTanggalDeadline(etTanggalDeadlineBaru.getText().toString());
                            listTugas.get(getAdapterPosition()).setKategoriTugas(kategoriTugasBaru[0]);
                            listTugas.get(getAdapterPosition()).setCatatan(etCatatanBaru.getText().toString());


                            diaglogEditTugas.dismiss();
                            Tugas tugas = listTugas.get(getAdapterPosition());
                            tugas.setExpandable(!tugas.isExpandable());
                            notifyItemChanged(getAdapterPosition());
                        }
                    });
                }
            });

            // Done tugas
            ivDoneTugas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            // Delete tugas
            ivDeleteTugas.setOnClickListener(new View.OnClickListener() {
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
                            Tugas tugas = listTugas.get(getAdapterPosition());
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
    }

}

