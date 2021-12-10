package com.practikum.naumen.service;

import com.practikum.naumen.models.Account;
import com.practikum.naumen.models.Role;
import com.practikum.naumen.repo.AccountRepository;
import com.practikum.naumen.repo.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    org.springframework.security.crypto.password.PasswordEncoder PasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
//        return new UserService(account.getId(), account.getUsername(), account.getPassword(), account.getRoles()
//                .stream()
//                .map(Account::new)
//                .collect(Collectors.toList()));
        return account;
    }

    public Account findAccountById(Long accountId) {
        Optional<Account> userFromDb = accountRepository.findById(accountId);
        return userFromDb.orElse(new Account());
    }

    public List<Account> allAccounts() {
        return accountRepository.findAll();
    }

    public boolean saveUser(Account account) {
        Account userFromDB = accountRepository.findByUsername(account.getUsername());
        if (userFromDB != null) {
            return false;
        }
      //  account.setRoles(Collections.singleton(new Role(1L, "ROLE_ADMIN")));
        account.setPassword(PasswordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return true;
    }

//    public boolean deleteAccount(Long accountId) {
//        if (accountRepository.findById(accountId).isPresent()) {
//            accountRepository.deleteById(accountId);
//            return true;
//        }
//        return false;
//    }

//    public List<Account> accountgtList(Long idMin) {
//        return em.createQuery("SELECT a FROM Account a WHERE a.id > :paramId", Account.class)
//                .setParameter("paramId", idMin).getResultList();
//    }
}

