package ru.musin.homework13;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        CrudHuman crudHuman = new CrudHumanImpl();
        List<Human> humans = crudHuman.getAllHumans(); //теперь в humans весь список людей из бд
        System.out.println(humans.size());
        Human human = crudHuman.getHumanById(3); //теперь в human находится конкретный человек (если такого id нет, то решение проблемы - на Ваше усмотрение)
        System.out.println(human);
        human.setName("Mitrofan");
        crudHuman.updateHuman(human); // теперь значения у данного человека в бд должно измениться
        crudHuman.deleteHuman(human); //после выполнения данной команды - данный человек должен удалиться из бд
        Human human1 = new Human();
        human1.setId(3);
        human1.setName("Andrei");
        human1.setLastName("Alekseev");
        human1.setPatronymic("Alekseevich");
        human1.setCity("Java city");
        human1.setStreet("StreetJava");
        human1.setFlat("FlatJava");
        human1.setHouse("HouseJava");
        human1.setNumberPassport("1232 12312");
        crudHuman.createHuman(human);
    }


}
