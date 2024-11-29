package com.eComStore.eCommerceBackend.DAOs;

import com.eComStore.eCommerceBackend.Models.Admin;

import java.util.Optional;

public interface AdminDAO {
    Optional<Admin> findByUsername(String username);

    Admin save(Admin admin);

}
