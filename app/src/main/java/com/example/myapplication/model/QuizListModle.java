package com.example.myapplication.model;

import com.google.firebase.firestore.DocumentId;

public class QuizListModle {
    @DocumentId
    private String que_id;
    private String questions;
    private String name,image,level,description,visibility;

    public QuizListModle(){

    }

    public QuizListModle(String que_id, String question, String name, String image, String level, String description, String visibility) {
        this.que_id = que_id;
        this.questions = question;
        this.name = name;
        this.image = image;
        this.level = level;
        this.description = description;
        this.visibility = visibility;
    }

    public String getQue_id() {
        return que_id;
    }

    public void setQue_id(String que_id) {
        this.que_id = que_id;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
