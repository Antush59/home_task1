package ru.innopolis.java.attestation.attestation01.repositories;

import ru.innopolis.java.attestation.attestation01.model.User;

import java.util.List;

public interface UsersRepository {

    void create(User user);

    User findById(String id);

    List<User> findAll();

    void update(User user);

    void deleteById(String id);

    List<User> findByAge(String age);

    List<User> findByIsWorker(String age);

    void deleteAll();
}
