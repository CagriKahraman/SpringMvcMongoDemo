package com.jcg.springmvc.mongo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jcg.springmvc.mongo.Customer;
import com.jcg.springmvc.mongo.CustomerService;

@Controller
@RequestMapping("/customer")
public class UserController {

	private static Logger log = Logger.getLogger(UserController.class);

	@Resource(name="customerService")
	private CustomerService customerService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getPersons(Model model) {
		log.debug("Request to fetch all customers from the mongo database");
		List<Customer> customer_list = customerService.getAll();		
		model.addAttribute("customers", customer_list);		
		return "welcome";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUser(Model model) {
		log.debug("Request to open the new customer form page");
		model.addAttribute("userAttr", new Customer());
		return "form";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editUser(@RequestParam(value="id", required=true) String id, Model model) {
		log.debug("Request to open the edit customer form page");	
		model.addAttribute("userAttr", customerService.findCustomerId(id));		
		return "form";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value="id", required=true) String id, Model model) {
		customerService.delete(id);
		return "redirect:list";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("userAttr") Customer customer) {
		if(customer.getId() != null && !customer.getId().trim().equals("")) {
			customerService.edit(customer);
		} else {
			customerService.add(customer);
		}
		return "redirect:list";
	}
}