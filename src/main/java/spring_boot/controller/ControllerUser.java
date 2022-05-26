package spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring_boot.entity.Role;
import spring_boot.entity.User;
import spring_boot.service.RoleServiceImpl;
import spring_boot.service.SecurityService;
import spring_boot.service.UserDetailServiceImpl;
import spring_boot.service.UserService;
import spring_boot.validator.UserValidator;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ControllerUser {

    private final UserDetailServiceImpl userDetailServiceImpl;
    private final RoleServiceImpl roleServiceImpl;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

//    @Autowired
//    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    public ControllerUser(UserDetailServiceImpl userDetailServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userDetailServiceImpl = userDetailServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

//
//    @GetMapping("/registration")
//    public String registration(Model model) {
//        model.addAttribute("userForm", new User());
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String registration(@ModelAttribute("userForm") User user, BindingResult bindingResult, Model model) {
//        userValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//        userService.saveUser(user);
//        securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());
//        return "redirect:/welcome";
//    }
//
//    @GetMapping("/login")
//    public String login(Model model, String error, String logout) {
//        if (error != null) {
//            model.addAttribute("error", "Username or password is incorrect");
//        }
//        if (logout != null) {
//            model.addAttribute("message", "Logged out successfully");
//        }
//        return "login";
//    }
//
//    @GetMapping({"/", "/welcome"})
//    public String welcome(Model model) {
//        return "welcome";
//    }
//
//    @GetMapping("/admin")
//    public String admin(Model model) {
//        return "admin";
//    }

    // начальная страница
    @GetMapping({"/", "/index"})
    public String indexPage() {
        return "index";
    }

    @GetMapping({"authorized"})
    public String authorizedPage() {
        return "authorized";
    }

    @RequestMapping("user/")
    public String user(Model model, Principal principal) {
        System.out.println("showAllUsers/allUsers");
        List<User> allUsers = new ArrayList<>();
        User user = userDetailServiceImpl.findByUsername(principal.getName());
        allUsers.add(user);
        model.addAttribute("userList", allUsers);
        return "user";
    }

}
