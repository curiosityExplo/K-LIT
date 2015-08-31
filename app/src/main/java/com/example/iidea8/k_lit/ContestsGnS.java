package com.example.iidea8.k_lit;

/**
 * Created by Abhigyan on 8/31/2015.
 */
public class ContestsGnS {
    String question;
    String optA;
    String optB;
    String optC;
    String optD;
    String answer;
    int queID;

    public ContestsGnS(){
        question = "";
        optA = "";
        optB = "";
        optC = "";
        optD = "";
        answer = "";
        queID = 0;
    }
    public ContestsGnS(int queID){
        this.queID = queID;
    }

    public  ContestsGnS(String question, String optA, String optB, String optC, String optD, String answer){
        this.question =question;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.optD= optD;
        this.answer = answer;
    }

    public int getQueID() {
        return queID;
    }

    public void setQueID(int queID) {
        this.queID = queID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptA() {
        return optA;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public String getOptB() {
        return optB;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }

    public String getOptC() {
        return optC;
    }

    public void setOptC(String optC) {
        this.optC = optC;
    }

    public String getOptD() {
        return optD;
    }

    public void setOptD(String optD) {
        this.optD = optD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
