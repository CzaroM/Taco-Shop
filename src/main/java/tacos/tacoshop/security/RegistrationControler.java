package tacos.tacoshop.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.tacoshop.data.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationControler {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationControler(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(){
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm registrationForm){
        userRepo.save(registrationForm.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
