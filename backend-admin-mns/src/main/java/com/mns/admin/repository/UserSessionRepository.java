package com.mns.admin.repository;

import com.mns.admin.model.Utilisateur;
import com.mns.admin.model.UtilisateurSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSessionRepository extends JpaRepository<UtilisateurSession, Long> {
    boolean existsByUtilisateur(Utilisateur utilisateur);
}
