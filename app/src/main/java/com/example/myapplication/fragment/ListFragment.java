package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.QuizlistViewModel;
import com.example.myapplication.R;
import com.example.myapplication.adapter.QuizlistAdapter;
import com.example.myapplication.model.QuizListModle;

import java.util.List;


public class ListFragment extends Fragment implements QuizlistAdapter.onquizelistbtnselect {


    private RecyclerView listView;
    QuizlistViewModel viewModel;
    private QuizlistAdapter adapter;
    LinearLayoutManager vertical;
    NavController navigate;


    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=view.findViewById(R.id.rv_list);

        navigate= Navigation.findNavController(view);

        adapter=new QuizlistAdapter(this);
        vertical=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        listView.setLayoutManager(vertical);
        listView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel=new ViewModelProvider(getActivity()).get(QuizlistViewModel.class);
        viewModel.getQuizlivedata().observe(getViewLifecycleOwner(), new Observer<List<QuizListModle>>() {
            @Override
            public void onChanged(List<QuizListModle> quizListModles) {

                adapter.setQuizListModles(quizListModles);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onclickbtn(int position) {

        ListFragmentDirections.ActionListFragmentToDetailFragment dtail=ListFragmentDirections.actionListFragmentToDetailFragment();
        dtail.setPosition(position);
        navigate.navigate(dtail);

    }
}