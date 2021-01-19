package com.example.myapplication;

import androidx.annotation.NonNull;

import com.example.myapplication.model.QuizListModle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class FirebaseRepo {
    private oncompletequizdata oncompletequizdata;

    private FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
    private CollectionReference quizef=firebaseFirestore.collection("QuizShow");
    public FirebaseRepo(FirebaseRepo.oncompletequizdata oncompletequizdata){
        this.oncompletequizdata=oncompletequizdata;
    }


    public void getquixdata(){
        quizef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
             if (task.isSuccessful()){

                 oncompletequizdata.addquizdata(task.getResult().toObjects(QuizListModle.class));
             }else {
                 oncompletequizdata.error(task.getException());
             }
            }
        });
    }
    public interface oncompletequizdata{
        void addquizdata(List<QuizListModle>quizListModles);
        void error(Exception e);

    }
}
