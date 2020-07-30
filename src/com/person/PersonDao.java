package com.person;

import java.util.ArrayList;
import java.util.Optional;

public class PersonDao {
    ArrayList<Person> persons;
    public PersonDao(ArrayList<Person> persons){
        this.persons = persons;
    }

    public boolean addPerson(Person p){
        if (checkExistFromName(p.name))
            return false;
        persons.add(p);
        return true;
    }

    public boolean checkPerson(String name, String pwd){
        Optional<Person> person = persons.stream().filter(item -> item.name == name).findFirst();
        if (checkExistFromName(name) && getPersonByName(name).password.equals(pwd))
            return true;
        else
            return false;
    }


    public boolean checkExistFromName(String name){
        return persons.stream().filter(item -> item.name.equals(name)).findFirst().isPresent();
    }

    public Person getPersonByName(String name){
        if (checkExistFromName(name))
            return persons.stream().filter(item -> item.name.equals(name)).findFirst().get();
        else
            return null;
    }
}
