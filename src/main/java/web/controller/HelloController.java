package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class HelloController {
	private UserService userService;
	private RoleService roleService;

	@Autowired
	public HelloController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@GetMapping
	public ModelAndView mainAdminControllerGet(Authentication auth){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("authUser",auth.getPrincipal());
		modelAndView.addObject("users",userService.listUsers());
		modelAndView.addObject("userEmail",((User) auth.getPrincipal()).getEmail());
		modelAndView.addObject("user", new User());
		modelAndView.addObject("rolesAuth",((User) auth.getPrincipal()).getRoles()
				.stream().map(Objects::toString).collect(Collectors.joining(" ")));
		modelAndView.setViewName("admin/listusers");
		return modelAndView;
	}

	@PostMapping("/add")
	public ModelAndView addUserControllerPost(@ModelAttribute("user") @Valid User user,
											  @RequestParam(value = "rolesFromHtml") String rolesFromHtml) {
		ModelAndView mv = new ModelAndView();
		user.setRoles(getSetRoles(rolesFromHtml));
		userService.add(user);
		mv.setViewName("redirect:/admin");
		return mv;
	}

	@PostMapping("/edit")
	public ModelAndView editUserControllerPost(@ModelAttribute @Valid User user,
											   @RequestParam(value = "rolesFromHtml") String rolesFromHtml) {
		ModelAndView mv = new ModelAndView();
		user.setRoles(getSetRoles(rolesFromHtml));
		userService.update(user);
		mv.setViewName("redirect:/admin");
		return mv;
	}

	@PostMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id){
		userService.delete(id);
		return "redirect:/admin";
	}

	public Set<Role> getSetRoles(String role){
		Set<Role> userRoles = new HashSet<>();
		if (role.equals("user")) {
			userRoles.add(roleService.getRole("USER"));
		}
		if (role.equals("admin")) {
			userRoles.add(roleService.getRole("ADMIN"));
		}
		if (role.equals("adminAndUser")) {
			userRoles.add(roleService.getRole("ADMIN"));
			userRoles.add(roleService.getRole("USER"));
		}
		return userRoles;
	}
}