package co.fkch.controller;

import co.fkch.controller.dto.CreateUserDTO;
import co.fkch.controller.dto.EmailDTO;
import co.fkch.domain.User;
import co.fkch.exception.ErrorCode;
import co.fkch.exception.RegisterException;
import co.fkch.service.EmailService;
import co.fkch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
            throw new RegisterException("There is already registered user with the email provided.",
                    ErrorCode.USER_EXIST);
        }
        User user = new User(createUserDTO.getUserName(), createUserDTO.getEmail(),
                createUserDTO.getFirstName(), createUserDTO.getLastName());
        user.setEnabled(false);
        // Generate random 36-character string token for confirmation link
        user.setConfirmationToken(UUID.randomUUID().toString());
        user = userService.saveUser(user);
        sendConfirmationEmail(user, request);
        return new ResponseEntity<>(new GeneralResponse<>(
                "A confirmation e-mail has been sent to " + user.getEmail()), HttpStatus.OK);
    }

    @RequestMapping(value = "/reconfirm", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity reconfirmEmail(@Valid @RequestBody EmailDTO emailDTO, HttpServletRequest request) {
        User user = userService.findByEmailNoPassword(emailDTO.getEmail());
        if (user != null) {
            if (user.isEnabled()) {
                throw new RegisterException("The user with the email provided already confirmed.",
                        ErrorCode.USER_EXIST_CONFIRMED);
            } else {
                user.setConfirmationToken(UUID.randomUUID().toString());
                user = userService.saveUser(user);
                sendConfirmationEmail(user, request);
            }
        } else {
            throw new RegisterException("The user with the email provided not found.",
                    ErrorCode.USER_NOT_FOUND);
        }
        return new ResponseEntity<>(new GeneralResponse<>(
                "A confirmation e-mail has been resent to " + user.getEmail()), HttpStatus.OK);
    }

    private void sendConfirmationEmail(User user, HttpServletRequest request) {
        String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(user.getEmail());
        registrationEmail.setSubject("Registration Confirmation");
        registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                + appUrl + "/confirm?token=" + user.getConfirmationToken());
        registrationEmail.setFrom("noreply@domain.com");
        emailService.sendEmail(registrationEmail);
    }


    //    // Process confirmation link
//    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
//    public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult,
//                                                @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {
//        modelAndView.setViewName("confirm");
//        Zxcvbn passwordCheck = new Zxcvbn();
//        Strength strength = passwordCheck.measure(requestParams.get("password"));
//        if (strength.getScore() < 3) {
//            bindingResult.reject("password");
//            redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");
//            modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
//            System.out.println(requestParams.get("token"));
//            return modelAndView;
//        }
//
//        // Find the user associated with the reset token
//        User user = userService.findByConfirmationToken(requestParams.get("token"));
//
//        // Set new password
//        user.setPassword(passwordEncoder.encode(requestParams.get("password")));
//
//        // Set user to enabled
//        user.setEnabled(true);
//
//        // Save user
//        userService.saveUser(user);
//
//        modelAndView.addObject("successMessage", "Your password has been set!");
//        return modelAndView;
//    }


}
