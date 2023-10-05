package com.github.jrtb.javarushtelegrambot.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tg_user")
public class  TelegramUser {

    @Id
    @Column(name = "chatId")
    private String chatId;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<GroupSub> groupSubs;
}
