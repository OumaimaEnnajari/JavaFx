package com.example.javafxdemo.metiers;


import java.util.List;
import java.util.Optional;

public interface CRUD<T,PK> {
    public boolean Create(T object);
    public List<T> All();
    Optional<T> Read(PK Id);
    boolean Update(T object , PK Id);
    //boolean Delete(T object);
    boolean Delete(PK Id);
    Long Count();
}