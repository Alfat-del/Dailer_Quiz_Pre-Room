package id.prodigy.dailer;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class TugasSelesaiFragment extends Fragment {
    RecyclerView recyclerView;
    private TugasSelesaiAdapter tugasSelesaiAdapter;
    private ArrayList<Tugas> tugasSelesaiArrayList;

    public TugasSelesaiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tugas_selesai, container, false);

        addData();
        tugasSelesaiAdapter = new TugasSelesaiAdapter(tugasSelesaiArrayList);
        recyclerView = view.findViewById(R.id.recycleview_tugasselesai);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(tugasSelesaiAdapter);


        return view;
    }

    public void addData() {
        tugasSelesaiArrayList = new ArrayList<>();
        tugasSelesaiArrayList.add(new Tugas(
                "Matematika Saintek",
                "Tiga Dimensi",
                "23:59",
                "01-01-2021",
                "Tugas Individu",
                "Kalo udah bisa, cobain yang teseract"));

        tugasSelesaiArrayList.add(new Tugas(
                "Sejarah Indonesia",
                "Perlayaran Nusantara",
                "23:59",
                "01-01-2021",
                "Tugas Individu",
                "-"));

        tugasSelesaiArrayList.add(new Tugas(
                "Bahasa Indonesia",
                "Pidato",
                "23:59",
                "02-01-2021",
                "Tugas Kelompok",
                "Bikin naskah pidatonya dari youtube stand up comedy tentang kapal presiden"));

    }

}