package saraya.services.data;

import saraya.entities.Session;
import saraya.repositories.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Optional<Session> getById(Integer id) {
        return sessionRepository.findById(id);
    }

    public Session save(Session session) {
        return sessionRepository.save(session);
    }

    public Session update(Session session, Integer id) {
        session.setId(id);
        return sessionRepository.save(session);
    }

    public void delete(Integer id) {
        sessionRepository.deleteById(id);
    }
}
