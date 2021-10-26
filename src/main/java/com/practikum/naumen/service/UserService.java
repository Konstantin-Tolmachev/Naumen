package com.practikum.naumen.service;

import com.practikum.naumen.models.Account;
import com.practikum.naumen.repo.AccountRepository;
import com.practikum.naumen.repo.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return account;
    }

    public Account findUserById(Long userId) {
        Optional<Account> userFromDb = accountRepository.findById(userId);
        return userFromDb.orElse(new Account());
    }

    public List<Account> allUsers() {
        return accountRepository.findAll();
    }

    public boolean saveUser(Account account) {
        Account userFromDB = accountRepository.findByUsername(account.getUsername());
        if (userFromDB != null) {
            return false;
        }
//        account.setRoles(Collections.singleton(new Role(2L, "ROLE_STAFF")));
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (accountRepository.findById(userId).isPresent()) {
            accountRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<Account> usergtList(Long idMin) {
        return em.createQuery("SELECT a FROM Account a WHERE a.id > :paramId", Account.class)
                .setParameter("paramId", idMin).getResultList();
    }
}

