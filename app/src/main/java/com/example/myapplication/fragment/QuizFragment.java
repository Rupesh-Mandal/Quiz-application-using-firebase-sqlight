package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.QuestionModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class QuizFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    private String quizid;
    private TextView quiz_title;
    private List<QuestionModel> allquestion=new ArrayList<>();
    private List<QuestionModel> selectedqueston=new ArrayList<>();
    private int numberOfQuestion = 5;

    //ui
    private TextView title,que_number,que_time,que_que,feedback;
    private Button option_a,option_b,option_c,btn_next;
    private ProgressBar progressBar;
    private CountDownTimer countDownTimer;
    private boolean canAnswer=false;
    private int currentValu=0;


    public QuizFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
        
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseFirestore=FirebaseFirestore.getInstance();
        quizid=QuizFragmentArgs.fromBundle(getArguments()).getQuizid();
        quiz_title=view.findViewById(R.id.textView5);
        inisilisView(view);
        firebaseFirestore.collection("QuizShow")
                .document(quizid)
                .collection("Questions")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){

                    allquestion=task.getResult().toObjects(QuestionModel.class);
                    takaquestion();
                    updateUI();

                }else {
                    quiz_title.setText("Error" + task.getException().getMessage());
                    Toast.makeText(getContext(), "question not get. task is null", Toast.LENGTH_SHORT).show();
                }

            }
        });
        option_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAnswer(option_a.getText());

            }
        });
        option_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAnswer(option_b.getText());

            }
        });
        option_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAnswer(option_c.getText());

            }
        });
    }

    private void selectAnswer(CharSequence text) {
        if (canAnswer){
            if (selectedqueston.get(currentValu).getAnswer().equals(text)){
                Log.e("answer","currect answer");
                Toast.makeText(getContext(), "right", Toast.LENGTH_SHORT).show();
                //amswer is currect
            }else {
                //answer incurrect
                Toast.makeText(getContext(), "wrong", Toast.LENGTH_SHORT).show();
                Log.e("answer","incurrect answer");
            }
        }

    }

    private void updateUI() {
        //quezDataLoading
        quiz_title.setText("Quiz Data Loaded");
        que_que.setText("load first question");

        enebleOption();
        loadquestion(1);
    }

    private void loadquestion(int questionnumber) {
        que_que.setText(selectedqueston.get(questionnumber).getQuestion());
        option_a.setText(selectedqueston.get(questionnumber).getOption_a());
        option_b.setText(selectedqueston.get(questionnumber).getOption_b());
        option_c.setText(selectedqueston.get(questionnumber).getOption_c());

        startTimer(questionnumber);
        canAnswer=true;

    }

    private void startTimer(int questionnumber) {
        final Long timeToAnswer=selectedqueston.get(questionnumber).getTimer();
        que_time.setText(timeToAnswer.toString());
        progressBar.setVisibility(View.VISIBLE);

        final Long timeToAnswerInMilliSecond=timeToAnswer*1000;

        //start Timer
        countDownTimer=new CountDownTimer(timeToAnswer*1000,10) {
            @Override
            public void onTick(long l) {
                que_time.setText(l/1000 + "");

                //progress in persenteg
                Long percenteg=l/(timeToAnswerInMilliSecond*1000);
                progressBar.setProgress(percenteg.intValue());


            }

            @Override
            public void onFinish() {
                canAnswer=false;

            }
        }.start();


    }

    private void enebleOption() {
        option_a.setVisibility(View.VISIBLE);
        option_b.setVisibility(View.VISIBLE);
        option_c.setVisibility(View.VISIBLE);

        //enable option
        option_a.setEnabled(true);
        option_b.setEnabled(true);
        option_c.setEnabled(true);

        //feedback and nexButton setInvisible
        feedback.setVisibility(View.INVISIBLE);
        btn_next.setVisibility(View.INVISIBLE);
        btn_next.setEnabled(true);
    }

    private void inisilisView(View view) {
        title=view.findViewById(R.id.textView5);
        que_number=view.findViewById(R.id.textView7);
        que_time=view.findViewById(R.id.textView6);
        que_que=view.findViewById(R.id.tv_question);
        feedback=view.findViewById(R.id.tv_feedback);
        option_a=view.findViewById(R.id.option_1);
        option_b=view.findViewById(R.id.option_2);
        option_c=view.findViewById(R.id.option_3);
        btn_next=view.findViewById(R.id.btn_next);
        progressBar=view.findViewById(R.id.progressBar2);
       }

    private void takaquestion() {
        for (int i=0; i<numberOfQuestion;i++){
            int randomnumber=getRandomInteger(allquestion.size(),0);
            selectedqueston.add(allquestion.get(randomnumber));
        }

    }

    public static int getRandomInteger(int max, int min){
        return ((int) (Math.random()*(max-min)))+min;
    }
}