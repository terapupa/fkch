package co.fkch.controller;

//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;


//@Controller
public class RegisterController {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private AccountService userService;
//    @Autowired
//    private EmailService emailService;

//    // Return registration form template
//    @RequestMapping(value = "/register", method = RequestMethod.GET)
//    public ModelAndView showRegistrationPage(ModelAndView modelAndView, Account user) {
//        modelAndView.addObject("user", user);
//        modelAndView.setViewName("register");
//        return modelAndView;
//    }
//
//    // Process form input data
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid Account user,
//                                                BindingResult bindingResult, HttpServletRequest request) {
//        Account userExists = userService.findByEmailNoPassword(user.getEmail());
//        System.out.println(userExists);
//        if (userExists != null) {
//            modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
//            modelAndView.setViewName("register");
//            bindingResult.reject("email");
//        }
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("register");
//        } else { // new user so we create user and send confirmation e-mail
//            // Disable user until they click on confirmation link in email
//            user.setEnabled(false);
//
//            // Generate random 36-character string token for confirmation link
//            user.setConfirmationToken(UUID.randomUUID().toString());
//            userService.saveUser(user);
//            String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
//            SimpleMailMessage registrationEmail = new SimpleMailMessage();
//            registrationEmail.setTo(user.getEmail());
//            registrationEmail.setSubject("Registration Confirmation");
//            registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
//                    + appUrl + "/confirm?token=" + user.getConfirmationToken());
//            registrationEmail.setFrom("noreply@domain.com");
//            emailService.sendEmail(registrationEmail);
//            modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
//            modelAndView.setViewName("register");
//        }
//        return modelAndView;
//    }
//
//    // Process confirmation link
//    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
//    public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {
//        Account user = userService.findByConfirmationToken(token);
//        if (user == null) { // No token found in DB
//            modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
//        } else { // Token found
//            modelAndView.addObject("confirmationToken", user.getConfirmationToken());
//        }
//        modelAndView.setViewName("confirm");
//        return modelAndView;
//    }
//
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
//        Account user = userService.findByConfirmationToken(requestParams.get("token"));
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
