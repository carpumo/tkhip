package com.cpm.tkhip.aop.empresa;

import com.cpm.tkhip.domain.User;
import com.cpm.tkhip.domain.parameters.EmpresaParameter;
import com.cpm.tkhip.repository.UserRepository;
import com.cpm.tkhip.security.SecurityUtils;
import com.cpm.tkhip.service.UserService;
import com.cpm.tkhip.service.dto.UserDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Aspect
@Component
public class UserAspect {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmpresaParameter EmpresaParameter;

    @Autowired
    UserService userService;

    private final String fieldName =  "empresaId";

    private final Logger log = LoggerFactory.getLogger(UserAspect.class);

    /**
     * Run method if User service createUser is hit.
     * Stores tenant information from DTO.
     */
    @Before(value = "execution(* com.cpm.tkhip.service.UserService.createUser(..)) && args(userDTO, ..)")
    public void onCreateUser(JoinPoint joinPoint, UserDTO userDTO) throws Throwable {
        Optional<String> login = SecurityUtils.getCurrentUserLogin();

        if(login.isPresent()) {
            User loggedInUser = userRepository.findOneByLogin(login.get()).get();

            if (loggedInUser.getEmpresa() != null) {
                EmpresaParameter.setEmpresa(loggedInUser.getEmpresa());
            }
//            else{
//                 EmpresaParameter.setEmpresa(userDTO.getEmpresa());
//            }
        }
    }

    /**
     * Run method if User service updateUser is hit.
     * Adds tenant information to DTO.
     */
    @Before(value = "execution(*  com.cpm.tkhip.service.UserService.updateUser(..)) && args(userDTO, ..)")
    public void onUpdateUser(JoinPoint joinPoint, UserDTO userDTO)
    {
        Optional<String> login = SecurityUtils.getCurrentUserLogin();

        if (login.isPresent())
        {
            User loggedInUser = userRepository.findOneByLogin(login.get()).get();
            User user = userRepository.findById(userDTO.getId()).get();

            if (loggedInUser.getEmpresa() != null)
            {
                user.setEmpresa(loggedInUser.getEmpresa());
            }
//            else
//            {
//                user.setEmpresa(userDTO.getEmpresa());
//            }

            log.debug("Changed Empresa for User: {}", user);
        }
    }

    /**
     * Run method if User repository save is hit.
     * Adds tenant information to DTO.
     */
    @Before(value = "execution(* com.cpm.tkhip.repository.UserRepository.save(..)) && args(user, ..)")
    public void onSave(JoinPoint joinPoint, User user) {
        Optional<String> login = SecurityUtils.getCurrentUserLogin();

        if(EmpresaParameter.getEmpresa() != null) {
            log.debug(EmpresaParameter.getEmpresa().getNombre());
            user.setEmpresa(EmpresaParameter.getEmpresa());
        } else {
            log.debug("No hay tu tia");
        }
    }

    /**
     * Run method if User service getUserWithAuthoritiesByLogin is hit.
     * Adds filtering to prevent display of information from another tenant
     */
    @Before(value = "execution(* com.cpm.tkhip.service.UserService.getUserWithAuthoritiesByLogin(..)) && args(login, ..)")
    public void onGetUserWithAuthoritiesByLogin(JoinPoint joinPoint, String login) throws Exception {
        log.debug("=============================beforeonGetUserWithAuthoritiesByLogin: " );
        Optional<String> currentLogin = SecurityUtils.getCurrentUserLogin();

        if(currentLogin.isPresent()) {
            User loggedInUser = userRepository.findOneByLogin(currentLogin.get()).get();

            if (loggedInUser.getEmpresa() != null) {
                log.debug("++++++++++++++++++++++++beforeonGetUserWithAuthoritiesByLogin EMPRESA: " + loggedInUser.getEmpresa());
                User user = userRepository.findOneWithAuthoritiesByLogin(login).get();

                if(!user.getEmpresa().equals(loggedInUser.getEmpresa())){
                    throw new NoSuchElementException();
                }
            }
        }
    }

    /**
     * Run method if User service getUserWithAuthorities is hit.
     * Adds filtering to prevent display of information from another tenant
     */
    @Before(value = "execution(* com.cpm.tkhip.service.UserService.getUserWithAuthorities(..)) && args(id, ..)")
    public void onGetUserWithAuthorities(JoinPoint joinPoint, Long id) throws Exception {
        Optional<String> currentLogin = SecurityUtils.getCurrentUserLogin();
        log.debug("=====================================beforeonGetUserWithAuthorities");
        if(currentLogin.isPresent()) {
            User loggedInUser = userRepository.findOneByLogin(currentLogin.get()).get();

            if (loggedInUser.getEmpresa() != null) {
                log.debug("+++++++++++++++++++++++++++beforeonGetUserWithAuthorities EMPRESA: " + loggedInUser.getEmpresa());
                User user = userRepository.findOneWithAuthoritiesById(id).get();

                if(!user.getEmpresa().equals(loggedInUser.getEmpresa())){
                    throw new NoSuchElementException();
                }
            }
        }
    }

    /**
     * Run method if User service getUserWithAuthorities is hit.
     * Adds filtering to prevent display of information from another tenant
     */
    @Before(value = "execution(* com.cpm.tkhip.service.UserService.getAllManagedUsers(..)) && args(id, ..)")
    public void onGetAllManagedUsers(JoinPoint joinPoint, Long id) throws Exception {
        Optional<String> currentLogin = SecurityUtils.getCurrentUserLogin();
        log.debug("=====================================beforeonGetUserWithAuthorities");
        if(currentLogin.isPresent()) {
            User loggedInUser = userRepository.findOneByLogin(currentLogin.get()).get();

            if (loggedInUser.getEmpresa() != null) {
                log.debug("+++++++++++++++++++++++++++before onGetAllManagedUsers EMPRESA: " + loggedInUser.getEmpresa());
                User user = userRepository.findOneWithAuthoritiesById(id).get();

                if(!user.getEmpresa().equals(loggedInUser.getEmpresa())){
                    throw new NoSuchElementException();
                }
            }
        }
    }

    /**
     * Run method around Carta service findAll.
     * Adds filtering to prevent display of information from another tenant before database query (less performance hit).
     */
//    @Around("execution(* com.cpm.tkhip.service.UserService.getAllManagedUsers()))")
//    public Object ongetAllManagedUsers(ProceedingJoinPoint pjp) throws Throwable {
//        Optional<String> login = SecurityUtils.getCurrentUserLogin();
//
//
//        Object[] args = pjp.getArgs();
//
//        System.out.println("==============pasa por find all Around ==============");
//
//        if(login.isPresent())
//        {
//            User loggedInUser = userRepository.findOneByLogin(login.get()).get();
//
//            if (loggedInUser.getEmpresa() != null) {
//
//                Pageable pageable;
//                Page<User> users = userRepository.findAllByLoginNotAndEmpresa(((Pageable) args[0]) ,login.get(),loggedInUser.getEmpresa());
//                return users;
//            }
//        }
//        return pjp.proceed();
//    }


}
