package id.prodigy.dailer;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TugasFragment extends Fragment {
    RecyclerView recyclerView;
    private TugasAdapter tugasAdapter;
    private ArrayList<Tugas> tugasArrayList;

    public TugasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tugas, container, false);

        addData();
        tugasAdapter = new TugasAdapter(tugasArrayList);
        recyclerView = view.findViewById(R.id.recycleview_tugas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(tugasAdapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.btn_floating_add_tugas);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TugasBaruActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


    public void addData() {
        tugasArrayList = new ArrayList<>();
        tugasArrayList.add(new Tugas(
                "Aljabar Linear",
                "Implementasi regresi linear",
                "23:59",
                "01-01-2021",
                "Tugas Individu",
                "Pake data registrasi mahasiswa"));

        tugasArrayList.add(new Tugas(
                "Pemrograman Berorientasi Objek - TE",
                "Event-driven programming",
                "23:59",
                "01-01-2021",
                "Tugas Individu",
                "Buat program tentang pemesanan makanan di restoran"));

        tugasArrayList.add(new Tugas(
                "Proyek Perangkat Lunak 3",
                "Dokumen testing",
                "23:59",
                "02-01-2021",
                "Tugas Kelompok",
                "Kebagian yang searching home atau admin"));

        tugasArrayList.add(new Tugas(
                "Kewirausahaan",
                "Selling product",
                "23:59",
                "03-01-2021",
                "Tugas Individu",
                "Bagian lampiran masih belum semua dimasukin"));

        tugasArrayList.add(new Tugas(
                "Database - TE",
                "Normalisasi",
                "23:59",
                "04-01-2021",
                "Tugas Individu",
                "Sampe normal form BCNF"));

        tugasArrayList.add(new Tugas(
                "Database - PR",
                "PL/SQL",
                "23:59",
                "04-01-2021",
                "Tugas Individu",
                "Buat procedure / function 2"));

        tugasArrayList.add(new Tugas(
                "Pengantar Rekayasa Perangkat Lunak - PR",
                "Macam-macam SDLC",
                "23:59",
                "04-01-2021",
                "Tugas Kelompok",
                "Pengertian, positif, negatif, kapan harus dipake"));

        tugasArrayList.add(new Tugas(
                "Pengantar Rekayasa Perangkat Lunak - TE",
                "Data flow diagram",
                "23:59",
                "05-01-2021",
                "Tugas Kelompok",
                "Ngelanjutin dari yang event list"));

        tugasArrayList.add(new Tugas(
                "Pemrograman Berorientasi Objek - PR",
                "Unit testing",
                "23:59",
                "06-01-2021",
                "Tugas Individu",
                "-"));

        tugasArrayList.add(new Tugas(
                "Proyek Perangkat Lunak 3",
                "Logbook",
                "23:59",
                "07-01-2021",
                "Tugas Kelompok",
                "-"));

        tugasArrayList.add(new Tugas(
                "Kimia Dasar",
                "Tabel Periodik",
                "23:59",
                "09-01-2021",
                "Tugas Individu",
                "Lebih detilin dibagian gas mulia"));
    }
}