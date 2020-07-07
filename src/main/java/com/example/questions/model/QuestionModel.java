package com.example.questions.model;

import org.springframework.data.annotation.Id;

import java.util.Arrays;

public class QuestionModel {

    //local variables
    @Id
    private String id;
    private String name;
    enum Type{
        TextBox,
        Date,
        Number,
        ComboBox
    }
    private Type type;
    private String acceptedValues;
    private String[] options;
    private String userName;

    public QuestionModel(String name, Type type, String acceptedValues){

        this.name=name;
        this.type=type;
        //Structure acceptedValues accordingly to the selected type below-->
        if(type != null && type.equals(Type.ComboBox)|| type == Type.ComboBox){
           this.acceptedValues = acceptedValues;
            options=this.acceptedValues.split("\\r?\\n");
        }
    this.userName = "";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getAcceptedValues() {
        return acceptedValues;
    }

    public void setAcceptedValues(String acceptedValues) {
        this.acceptedValues=acceptedValues;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "QuestionModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", acceptedValues='" + acceptedValues + '\'' +
                ", options=" + Arrays.toString(options) +
                '}';
    }
}
