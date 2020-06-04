package com.derandecker.bakingapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Recipes")
public class Recipe {
    @PrimaryKey
    private int id;
    private String name;


    @Ignore
    public Recipe() {
    }

    public Recipe(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }


    public class Ingredients {

    }

    public class Steps {

    }
}

//can try embedded but probably need to do a new pojo for ingredients and steps
//so can account for differing number of ingredients and steps in each recipe

//public class Address {
//    public String street;
//    public String state;
//    public String city;
//
//    @ColumnInfo(name = "post_code") public int postCode;
//}
//
//@Entity
//public class User {
//    @PrimaryKey public int id;
//
//    public String firstName;
//
//    @Embedded public Address address;
//}