package com.example.myapplication;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.QuizListModle;

import java.util.List;

public class QuizlistViewModel extends ViewModel implements FirebaseRepo.oncompletequizdata {

    public MutableLiveData<List<QuizListModle>> getQuizlivedata() {
        return Quizlivedata;
    }

    MutableLiveData<List<QuizListModle>> Quizlivedata= new MutableLiveData<>();
  FirebaseRepo firebaseRepo=new FirebaseRepo(this);


    public QuizlistViewModel(){
        firebaseRepo.getquixdata();
    }

    @Override
    public void addquizdata(List<QuizListModle> quizListModles) {

        Quizlivedata.setValue(quizListModles);
    }

    @Override
    public void error(Exception e) {

    }
}
