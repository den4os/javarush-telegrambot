package com.github.jrtb.javarushtelegrambot.service;

import com.github.jrtb.javarushtelegrambot.javarushclient.dto.GroupDiscussionInfo;
import com.github.jrtb.javarushtelegrambot.repository.entity.GroupSub;

public interface GroupSubService {

    GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);
}
