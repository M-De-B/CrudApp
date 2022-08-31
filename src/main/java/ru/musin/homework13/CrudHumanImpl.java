package ru.musin.homework13;

import ru.musin.homework13.util.DBUtill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudHumanImpl implements CrudHuman {
    List<Human> people = new ArrayList<>();
    Connection connection = DBUtill.createConnection();

    @Override
    public List<Human> getAllHumans() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM People");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Human human = humanMapper(resultSet);

                people.add(human);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    @Override
    public Human getHumanById(int id) {
        Human human;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM People WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            human = humanMapper(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return human;
    }

    @Override
    public void createHuman(Human human) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO People(name,lastName,patronymic,city,street,house,flat,numberPassport,id) VALUES (?,?,?,?,?,?,?,?,?)");
            humanMapper(preparedStatement,human);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateHuman(Human human) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE People SET name=?, lastName=?, patronymic=?,city=?,street=?,house=?,flat=?,numberPassport=? where id=?");
            humanMapper(preparedStatement,human);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteHuman(Human human) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM People WHERE id=?"
            );
            preparedStatement.setInt(1,human.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Human humanMapper(ResultSet resultSet) throws SQLException {
        Human human = new Human();
        human.setId(resultSet.getInt("id"));
        human.setName(resultSet.getString("name"));
        human.setLastName(resultSet.getString("lastName"));
        human.setPatronymic(resultSet.getString("patronymic"));
        human.setCity(resultSet.getString("city"));
        human.setStreet(resultSet.getString("street"));
        human.setHouse(resultSet.getString("house"));
        human.setFlat(resultSet.getString("flat"));
        human.setNumberPassport(resultSet.getString("numberPassport"));
        return human;
    }

    private void humanMapper(PreparedStatement preparedStatement, Human human) throws SQLException {

        preparedStatement.setString(1, human.getName());
        preparedStatement.setString(2, human.getLastName());
        preparedStatement.setString(3, human.getPatronymic());
        preparedStatement.setString(4, human.getCity());
        preparedStatement.setString(5, human.getStreet());
        preparedStatement.setString(6, human.getHouse());
        preparedStatement.setString(7, human.getFlat());
        preparedStatement.setString(8, human.getNumberPassport());
        preparedStatement.setInt(9, human.getId());
    }
}
