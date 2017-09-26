package com.arpc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.arpc.spring.model.Person;
import com.arpc.spring.service.PersonService;

@Controller
public class PersonController {
	private PersonService personService;
	
	@Autowired(required=true)
	@Qualifier(value="personService")
	public void setPersonService(PersonService ps){
		this.personService=ps;
	}
	
	//http://localhost:8082/springHibernate/persons/
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String listPersons(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("listPersons", this.personService.listPersons());
		return "person";
	}
	
	//For add and update person both
	@RequestMapping(value= "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person p){

		if(p.getId() == 0){
			//new person, add it
			this.personService.addPerson(p);
		}else{
			//existing person, call update
			this.personService.updatePerson(p);
		}

		return "redirect:/persons";

	}
	@RequestMapping(value="/remove/{id}", method=RequestMethod.GET)
	public String removePerson(@PathVariable("id") int id){
		this.personService.removePerson(id);
		return "redirect:/persons";
	}
	@RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
		Person person=this.personService.getPersonById(id);
        model.addAttribute("person", person);
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }
}
