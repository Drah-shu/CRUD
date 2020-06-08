package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class HelloController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@GetMapping("")
	public String getAllUsers (ModelMap model) {
		List <User> theCustomers = userService.listUsers();
		model.addAttribute("users", theCustomers);
		return "admin/listusers";
	}

	@GetMapping("add")
	public String loadFormPage(Model model) {
		return "admin/add";
	}

	@PostMapping("add")
	public String add(@ModelAttribute("user") User user,
							HttpServletRequest request,
							HttpSession session ) {
		Set<Role> roles = user.getRoles();
		String admin = request.getParameter("admin");
		String usver = request.getParameter("user");

		if (admin != null) {
			roles.add(roleService.getRole("ADMIN"));
		}
		if (usver != null) {
			roles.add(roleService.getRole("USER"));
		}

		if (roles.size() == 0) {
			session.setAttribute("status","не выбрана");
			return "redirect:/admin";
		}

		user.setRoles(roles);
		if (userService.add(user)){
			return "redirect:/admin";
		} else {
			return "redirect:/admin";
		}

	}

	@GetMapping("edit")
	public String editForm(Model model) {
		return "admin/edit";
	}

	@PostMapping("edit")
	public String editUser(@ModelAttribute("user") User user,
								 HttpServletRequest request,
								 HttpSession session ) {
		Set<Role> roles = user.getRoles();
		String admin = request.getParameter("admin");
		String usver = request.getParameter("user");

		if (admin != null) {
			roles.add(roleService.getRole("ADMIN"));
		}
		if (usver != null) {
			roles.add(roleService.getRole("USER"));
		}
		if (roles.size() == 0) {
			session.setAttribute("status","Не выбрана");
			return "redirect:/admin";
		}

		user.setRoles(roles);
		userService.update(user);
		return "redirect:/admin";
	}

	@GetMapping("delete")
	public String deleteGet(ModelMap map) {
		return "admin/delete";
	}

	@PostMapping("delete")
	public String deletePost(@RequestParam("id") Long id) {
		userService.delete(id);
		return "redirect:/admin";
	}
}