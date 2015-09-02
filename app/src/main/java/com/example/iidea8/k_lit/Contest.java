package com.example.iidea8.k_lit;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;


public class Contest extends Fragment implements ContestResponse {
    View view;
    ContestsGnS contestsGnS;
    ContestsGnS newContestsGnS;
   public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://iidea8.webuda.com/services/";
    private RadioButton optA;
    private RadioButton optB;
    private RadioButton optC;
    private RadioButton optD;
    private RadioGroup radioGroup;
    private Button btNext;
    private Context cont;
    private int id =1;
    private ContestResponse contestResponse;
    int score = 0;
    private int i = 0;
    String selectedAnswer = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contest, container, false);
        TextView question = (TextView) view.findViewById(R.id.tv_question);
        contestResponse = this;
        optA = (RadioButton) view.findViewById(R.id.rb_optA);
        optB = (RadioButton) view.findViewById(R.id.rb_optB);
        optC = (RadioButton) view.findViewById(R.id.rb_optC);
        optD = (RadioButton) view.findViewById(R.id.rb_optD);
        btNext = (Button) view.findViewById(R.id.bt_next);
        radioGroup = (RadioGroup) view.findViewById(R.id.rg_contest);

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup.getCheckedRadioButtonId() == -1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Nothing Selected!");
                    builder.setMessage("Must choose an answer..");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }else {
                    int buttonId = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) view.findViewById(buttonId);
                    String selectedAnswer = (String) radioButton.getText();
                    if (newContestsGnS.answer.equals(selectedAnswer)) {
                        //Toast.makeText(getActivity(), "right", Toast.LENGTH_LONG).show();
                        score++;
                    }

                    //Toast.makeText(getActivity(), "wrong", Toast.LENGTH_LONG).show();

                setUpQuestion();
                radioGroup.clearCheck();
            }}
        });
        cont = getActivity();
        setUpQuestion();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Contest");
    }

    private void setUpQuestion(){
        if (i == 5){
            Fragment resultFrag = new ContestResult();
            Bundle bundle = new Bundle();
            bundle.putInt("answerNo", score);
            resultFrag.setArguments(bundle);
            getFragmentManager().beginTransaction().add(R.id.frame_layout, resultFrag).commit();

        }else {
            Random r = new Random();
            id = r.nextInt(10 - 1 + 1) + 1;
            contestsGnS = new ContestsGnS(id);
            new ContestAsync(cont, view, contestsGnS, contestResponse).execute();
            i++;
        }
    }

    @Override
    public void processFinish(ContestsGnS contestsGnS) {
        this.newContestsGnS = contestsGnS;
    }
}

