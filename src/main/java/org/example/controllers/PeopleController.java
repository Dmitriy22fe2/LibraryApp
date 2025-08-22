package org.example.controllers;

import org.example.DAO.PersonDAO;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PeopleController {

    private PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/people")
    public String people(Model model) {
        model.addAttribute("people", personDAO.getPeople());
        return "people";
    }

    @GetMapping("/people/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping("/people/add")
    public String addPerson(@ModelAttribute("person") Person person) {
        personDAO.addPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/people/{id}")
    public String person(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPersonById(id));
        return "people/person";
    }

    @GetMapping("/people/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPersonById(id));
        return "people/edit";
    }

    @PatchMapping("/people/{id}/edit")
    public String updatePerson(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        personDAO.updatePerson(person, id);
        return "redirect:/people/" + id;
    }

    @DeleteMapping("/people/{id}/delete")
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }
}
