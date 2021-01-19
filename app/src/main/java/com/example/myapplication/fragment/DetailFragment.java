package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.QuizlistViewModel;
import com.example.myapplication.R;
import com.example.myapplication.model.QuizListModle;

import java.util.List;


public class DetailFragment extends Fragment {


    NavController navController;
    QuizlistViewModel viewModel;
    ImageView imageView;
    TextView name,description,difficulty,total_question,last_score;
    Button startQuize;
    int position=0;
    private String quizid;

    public DetailFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        position=DetailFragmentArgs.fromBundle(getArguments()).getPosition();
        navController= Navigation.findNavController(view);
        imageView=view.findViewById(R.id.imagedtail);
        name=view.findViewById(R.id.tv_list);
        description=view.findViewById(R.id.description);
        difficulty=view.findViewById(R.id.textView4);
        total_question=view.findViewById(R.id.textView3);
        last_score=view.findViewById(R.id.tv_lastscore);
        startQuize=view.findViewById(R.id.button);

        startQuize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailFragmentDirections.ActionDetailFragmentToQuizFragment action=DetailFragmentDirections.actionDetailFragmentToQuizFragment();
                action.setQuizid(quizid);
                action.setPosition(position);
                navController.navigate(action);
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel=new ViewModelProvider(getActivity()).get(QuizlistViewModel.class);
        viewModel.getQuizlivedata().observe(getViewLifecycleOwner(), new Observer<List<QuizListModle>>() {
            @Override
            public void onChanged(List<QuizListModle> quizListModles) {
                Glide.with(getContext()).load(quizListModles.get(position).getImage()).centerCrop().placeholder(R.drawable.ic_launcher_background).into(imageView);

                name.setText(quizListModles.get(position).getName());
                description.setText(quizListModles.get(position).getDescription());
                difficulty.setText(quizListModles.get(position).getLevel());
                total_question.setText(quizListModles.get(position).getQuestions());
                quizid=quizListModles.get(position).getQue_id();


            }
        });
    }


}