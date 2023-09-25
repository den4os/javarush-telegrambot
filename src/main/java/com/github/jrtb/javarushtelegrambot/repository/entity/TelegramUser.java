package com.github.jrtb.javarushtelegrambot.repository.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tg_user")
public class TelegramUser {

    @Id
    @Column(name = "chatId")
    private String chatId;

    @Column(name = "active")
    private boolean active;
}
