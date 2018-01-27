package co.fkch.controller;

import co.fkch.controller.dto.CreateUserDTO;
import co.fkch.domain.User;
import co.fkch.exception.UserAlreadyExistException;
import co.fkch.service.EmailService;
import co.fkch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
public class RegisterRestController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createUserWithConfirmationEmail(@Valid @RequestBody CreateUserDTO createUserDTO, HttpServletRequest request) {
        if (userService.findByEmailNoPassword(createUserDTO.getEmail()) != null) {
            throw new UserAlreadyExistException("There is already a user registered with the email provided.");
        }
        User user = new User(createUserDTO.getUserName(), createUserDTO.getEmail(),
                createUserDTO.getFirstName(), createUserDTO.getLastName());
        user.setEnabled(false);
        // Generate random 36-character string token for confirmation link
        user.setConfirmationToken(UUID.randomUUID().toString());
        user = userService.saveUser(user);
        String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(user.getEmail());
        registrationEmail.setSubject("Registration Confirmation");
        registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                + appUrl + "/confirm?token=" + user.getConfirmationToken());
        registrationEmail.setFrom("noreply@domain.com");
        emailService.sendEmail(registrationEmail);
        return new ResponseEntity<>(new GeneralResponse<>(), HttpStatus.OK);
    }

}
