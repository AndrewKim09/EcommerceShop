package com.eComStore.eCommerceBackend.Services;

import com.eComStore.eCommerceBackend.DAOs.AdminDAOImpl;
import com.eComStore.eCommerceBackend.Models.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminDAOImpl adminDAO;

    public Optional<Admin> getAdminByUsername(String username){
        return adminDAO.findByUsername(username);
    }
}
