package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@Autowired
	private ProductService service;

	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		
		return "index";
	}

	@RequestMapping("/listusers")
	public String listUsers(Model model) {
		System.out.println("listusers:");
		List<User> userList = userService.listAll();
		model.addAttribute("userList", userList);

		return "listusers";
	}
	
	@RequestMapping("/new")
	public String showNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}

	@RequestMapping("/newuser")
	public String showNewUserForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);

		return "newuser";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		
		return "redirect:/";
	}

	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user) {
		userService.save(user);

		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_product");
		
		Product product = service.get(id);
		mav.addObject("product", product);
		
		return mav;
	}

	@RequestMapping("/edituser/{id}")
	public ModelAndView showEditUserForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_user");

		User user = userService.findById(id);
		mav.addObject("user", user);

		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/";
	}
}
