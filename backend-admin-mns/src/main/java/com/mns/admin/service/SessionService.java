package com.mns.admin.service;

import com.mns.admin.model.UtilisateurSession;
import com.mns.admin.repository.UserSessionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {
    private final UserSessionRepository sessionRepository;

    public SessionService(UserSessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void invalidateSession(String token) {
        Optional<UtilisateurSession> session = sessionRepository.findByToken(token);
        session.ifPresent(sessionRepository::delete);
    }
}
