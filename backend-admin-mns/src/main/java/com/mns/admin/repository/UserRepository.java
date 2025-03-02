package com.mns.admin.repository;

import com.mns.admin.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmailUtilisateur(String emailUtilisateur);
}
