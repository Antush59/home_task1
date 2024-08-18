package ru.innopolis.java.attestation.attestation01.repositories;

import ru.innopolis.java.attestation.attestation01.model.RegisteredUser;
import ru.innopolis.java.attestation.attestation01.model.User;

import java.util.List;

public interface UsersRepository {

    void create(User user);

    User findById(String id);

    List<RegisteredUser> findAll();

    void update(RegisteredUser user);

    void deleteById(String id);

    List<RegisteredUser> findByAge(String age);

    List<RegisteredUser> findByIsWorker(String age);

    void deleteAll();
}
