package com.example.projekt;

public class Characters {
    private  String name;
    private String location;
    private String  company;

    public Characters () {
        name="no name";
        location="no place";
        company="no clan";
    }
    public Characters (String n,String l,String c){
        name=n;
        location=l;
        company=c;
    }
    public String info(){
        String tmp=new String();
        tmp+=name+"lives in "+location+" and works for "+company+" clan.";
        return tmp;
    }
    public void setName(String n){
        name=n;
    }
    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return name;
    }
}
