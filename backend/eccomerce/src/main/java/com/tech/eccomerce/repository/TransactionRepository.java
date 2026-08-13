package com.tech.eccomerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.eccomerce.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
