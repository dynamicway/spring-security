package me.spring.security.user.testdouble;

import me.spring.security.user.UserRole;
import me.spring.security.user.UserRoleRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class SpyUserRoleRepository implements UserRoleRepository {
    public List<UserRole> saveAll_arguments;

    @Override
    public List<UserRole> findAll() {
        return null;
    }

    @Override
    public List<UserRole> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<UserRole> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<UserRole> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(UserRole entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserRole> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends UserRole> S save(S entity) {
        return null;
    }

    @Override
    public <S extends UserRole> List<S> saveAll(Iterable<S> entities) {
        saveAll_arguments = (List<UserRole>) entities;
        return null;
    }

    @Override
    public Optional<UserRole> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends UserRole> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends UserRole> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<UserRole> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public UserRole getOne(Long aLong) {
        return null;
    }

    @Override
    public UserRole getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends UserRole> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserRole> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends UserRole> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends UserRole> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserRole> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserRole> boolean exists(Example<S> example) {
        return false;
    }
}
