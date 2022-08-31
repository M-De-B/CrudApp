package ru.musin.homework13;

import java.util.List;

public interface CrudHuman {

    List<Human> getAllHumans(); //Возвращает список всех людей из базы данных
    Human getHumanById(int id); //Возвращает конкретного человека, у которого определенный id
    void createHuman(Human human); //Создает человека и записывает его в БД
    void updateHuman(Human human); //Обновляет данные по конкретному человеку
    void deleteHuman(Human human); //Удаляет конкретного человека
}
