package org.example.DAO;

import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT * FROM people", new BeanPropertyRowMapper<>(Person.class));
    }

    public void addPerson(Person person) {
        jdbcTemplate.update("INSERT INTO people (fullName, yearOfBirth) VALUES (?, ?);", person.getFullName(), person.getYearOfBirth());
    }

    public Person getPersonById(int id) {
        return jdbcTemplate.query("SELECT * FROM people WHERE personId = ?", new BeanPropertyRowMapper<>(Person.class), id).stream().findAny().orElse(null);
    }

    public void updatePerson(Person person, int id) {
        jdbcTemplate.update("UPDATE people SET fullName = ?, yearOfBirth = ? WHERE personId = ?;", person.getFullName(), person.getYearOfBirth(), id);
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM people WHERE personId = ?;", id);
    }
}
