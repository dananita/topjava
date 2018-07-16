package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.NullUser;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new User(null, "userName", "email1@mail.ru", "password", Role.ROLE_USER));
        save(new User(null, "userName", "email2@mail.ru", "password", Role.ROLE_ADMIN));
    }

    public static void main(String[] args) { //test
        InMemoryUserRepositoryImpl repository2 = new InMemoryUserRepositoryImpl();
        System.out.println(repository2.getAll());
        System.out.println("AD    :"+repository2.getByEmail("email@mail.ru").toString());
        System.out.println(repository2.get(3));
        System.out.println(repository2.delete(3));
        System.out.println(repository2.delete(2));
        repository2.save(new User(null, "userName3", "email1@mail.ru", "password", Role.ROLE_USER));
        System.out.println(repository2.getAll());
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repository.remove(id)!=null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        return repository.put(user.getId(), user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return repository.values().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
         return repository.entrySet().stream()
                    .filter(entry -> entry.getValue().getEmail().equalsIgnoreCase(email))
                    .map(Map.Entry::getValue)
                    .findFirst()
                    .orElse(null);
    }
}
