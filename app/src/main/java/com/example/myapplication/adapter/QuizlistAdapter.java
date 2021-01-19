package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.QuizListModle;

import java.util.List;

public class QuizlistAdapter extends RecyclerView.Adapter<QuizlistAdapter.ViewModel>{

    List<QuizListModle> quizListModles;
    onquizelistbtnselect onquizelistbtnselect;

    public QuizlistAdapter(onquizelistbtnselect onquizelistbtnselect) {
        this.onquizelistbtnselect = onquizelistbtnselect;
    }

    public void setQuizListModles(List<QuizListModle> quizListModles) {
        this.quizListModles = quizListModles;
    }

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_iteam,parent,false);

        return new ViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel holder, int position) {



        holder.TV_quizname.setText(quizListModles.get(position).getName());

        Glide.with(holder.itemView.getContext()).load(quizListModles.get(position).getImage()).centerCrop().placeholder(R.drawable.ic_launcher_background).into(holder.imageView);
        holder.TV_dis.setText(quizListModles.get(position).getDescription());
        holder.TV_difficulty.setText(quizListModles.get(position).getLevel());
    }

    @Override
    public int getItemCount() {
        if (quizListModles==null){
            return 0;
        }else {
            return quizListModles.size();
        }
    }

    public class ViewModel extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView TV_quizname,TV_dis,TV_difficulty;
        ImageView imageView;
        Button BTN_quizplay;
        public ViewModel(@NonNull View itemView) {
            super(itemView);
            TV_quizname=itemView.findViewById(R.id.textViewx);
            TV_dis=itemView.findViewById(R.id.discription2);
            TV_difficulty=itemView.findViewById(R.id.difficulty2);
            imageView=itemView.findViewById(R.id.imageView2);
            BTN_quizplay=itemView.findViewById(R.id.quiz_time);

            BTN_quizplay.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {


            onquizelistbtnselect.onclickbtn(getAdapterPosition());


        }
    }

    public interface onquizelistbtnselect{
        void onclickbtn(int position);
    }
}
