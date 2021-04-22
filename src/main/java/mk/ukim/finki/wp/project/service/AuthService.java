package mk.ukim.finki.wp.project.service;

import mk.ukim.finki.wp.project.model.User;

public interface AuthService {
    User login(String username, String password);
}
