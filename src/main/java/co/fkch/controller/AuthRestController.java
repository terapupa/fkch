package co.fkch.controller;

import co.fkch.controller.dto.CreateUserDto;
import co.fkch.controller.dto.EmailDto;
import co.fkch.controller.dto.LoginDto;
import co.fkch.controller.dto.NewPasswordDto;
import co.fkch.domain.Account;
import co.fkch.exception.AuthException;
import co.fkch.exception.ErrorCode;
import co.fkch.service.AccountService;
import co.fkch.service.EmailService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class AuthRestController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountService accountService;
    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/register")
    public GeneralResponse createUserWithConfirmationEmail(@Valid @RequestBody CreateUserDto createUserDTO, HttpServletRequest request) {
        if (accountService.findByEmailNoPassword(createUserDTO.getEmail()) != null) {
            throw new AuthException("There is already registered account with the email provided.",
                    ErrorCode.USER_EXIST);
        }
        Account account = new Account(createUserDTO.getUserName(), createUserDTO.getEmail(),
                createUserDTO.getFirstName(), createUserDTO.getLastName());
        account.setEnabled(false);
        // Generate random 36-character string token for confirmation link
        account.setConfirmationToken(UUID.randomUUID().toString());
        account = accountService.saveUser(account);
        sendConfirmationEmail(account, request);
        GeneralResponse gp = new GeneralResponse<>("A confirmation e-mail has been sent to " + account.getEmail());
        gp.add(linkTo(methodOn(AuthRestController.class).createUserWithConfirmationEmail(createUserDTO, request)).withSelfRel());
        return gp;
    }

    @PostMapping(value = "/resendEmail")
    public GeneralResponse resendEmail(@Valid @RequestBody EmailDto emailDto, HttpServletRequest request) {
        Account account = accountService.findByEmailNoPassword(emailDto.getEmail());
        if (account != null) {
            if (account.isEnabled()) {
                throw new AuthException("The account with the email provided already confirmed.",
                        ErrorCode.USER_EXIST_CONFIRMED);
            } else {
                account.setConfirmationToken(UUID.randomUUID().toString());
                account = accountService.saveUser(account);
                sendConfirmationEmail(account, request);
            }
        } else {
            throw new AuthException("The account with the email provided not found.",
                    ErrorCode.USER_NOT_FOUND);
        }
        GeneralResponse gp = new GeneralResponse<>("A confirmation e-mail has been resent to " + account.getEmail());
        gp.add(linkTo(methodOn(AuthRestController.class).resendEmail(emailDto, request)).withSelfRel());
        return gp;
    }

    private void sendConfirmationEmail(Account account, HttpServletRequest request) {
        String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(account.getEmail());
        registrationEmail.setSubject("Registration Confirmation");
        registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                + appUrl + "/confirm?token=" + account.getConfirmationToken());
        registrationEmail.setFrom("noreply@domain.com");
        emailService.sendEmail(registrationEmail);
    }

    // Process confirmation link
    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public GeneralResponse processConfirmationLink(@RequestParam("token") String token) {
        if (accountService.findByConfirmationToken(token) == null) {
            throw new AuthException("Oops! This is an invalid confirmation link.", ErrorCode.WRONG_CONFIRMATION_TOKEN);
        }
        GeneralResponse gp = new GeneralResponse<>(token);
        gp.add(linkTo(methodOn(AuthRestController.class).processConfirmationLink(token)).withSelfRel());
        return gp;
    }

    // Process confirmation link
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public GeneralResponse processConfirmation(@Valid @RequestBody NewPasswordDto newPasswordDto, @RequestParam String token) {
        if (!newPasswordDto.getPassword().equals(newPasswordDto.getRepeat())) {
            throw new AuthException("Mismatch passwords.", ErrorCode.PASSWORD_MISMATCH);
        }
        Strength strength = new Zxcvbn().measure(newPasswordDto.getPassword());
        if (strength.getScore() < 3) {
            throw new AuthException("Your password is too weak. Choose a stronger one.", ErrorCode.WEAK_PASSWORD);
        }
        // Find the account associated with the reset token
        Account account = accountService.findByConfirmationToken(token);
        if (account == null) {
            throw new AuthException("Oops! This is an invalid confirmation token.", ErrorCode.WRONG_CONFIRMATION_TOKEN);
        }
        account.setPassword(passwordEncoder.encode(newPasswordDto.getPassword()));
        account.setEnabled(true);
        accountService.saveUser(account);
        GeneralResponse gp = new GeneralResponse<>("OK");
        gp.add(linkTo(methodOn(AuthRestController.class).processConfirmation(newPasswordDto, token)).withSelfRel());
        return gp;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public GeneralResponse login(@Valid @RequestBody LoginDto loginDto) {
        Account account = accountService.findByEmailOrUserName(loginDto.getUserName());
        if (account == null) {
            throw new AuthException("User not found", ErrorCode.USER_NOT_FOUND);
        }
        if (!account.isEnabled()) {
            throw new AuthException("User not activated", ErrorCode.USER_NOT_ACTIVATED);
        }
        String p = loginDto.getPassword();
        if (StringUtils.isEmpty(p) || p.equals(passwordEncoder.encode(account.getPassword()))) {
            throw new AuthException("Wrong password", ErrorCode.WRONG_PASSWORD);
        }
        GeneralResponse gp = new GeneralResponse<>(Base64.encodeBase64String((account.getUserName()
                + ":" + account.getPassword()).getBytes()));
        gp.add(linkTo(methodOn(AuthRestController.class).login(loginDto)).withSelfRel());
        return gp;

    }
}
