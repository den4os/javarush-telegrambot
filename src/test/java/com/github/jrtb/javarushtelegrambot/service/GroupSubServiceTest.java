package com.github.jrtb.javarushtelegrambot.service;

import com.github.jrtb.javarushtelegrambot.javarushclient.dto.GroupDiscussionInfo;
import com.github.jrtb.javarushtelegrambot.repository.GroupSubRepository;
import com.github.jrtb.javarushtelegrambot.repository.entity.GroupSub;
import com.github.jrtb.javarushtelegrambot.repository.entity.TelegramUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

@DisplayName("Unit-level testing for GroupSubService")
public class GroupSubServiceTest {

    private GroupSubService groupSubService;
    private GroupSubRepository groupSubRepository;
    private TelegramUser newUser;

    private final static String CHAT_ID = "1";

    @BeforeEach
    public void init() {
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        groupSubRepository = Mockito.mock(GroupSubRepository.class);
        groupSubService = new GroupSubServiceImpl(groupSubRepository, telegramUserService);

        newUser = new TelegramUser();
        newUser.setActive(true);
        newUser.setChatId(CHAT_ID);

        Mockito.when(telegramUserService.findByChatId(CHAT_ID)).thenReturn(Optional.of(newUser));
    }

    @Test
    public void shouldProperlySaveGroup() {
        //given
        GroupDiscussionInfo discussionInfo = new GroupDiscussionInfo();
        discussionInfo.setId(1);
        discussionInfo.setTitle("g1");

        GroupSub expectedGroupSub = new GroupSub();
        expectedGroupSub.setId(discussionInfo.getId());
        expectedGroupSub.setTitle(discussionInfo.getTitle());
        expectedGroupSub.addUser(newUser);

        //when
        groupSubService.save(CHAT_ID, discussionInfo);

        //then
        Mockito.verify(groupSubRepository).save(expectedGroupSub);
    }

    @Test
    public void shouldProperlyAddUserToExistingGroup() {
        //given
        TelegramUser oldUser = new TelegramUser();
        oldUser.setChatId("2");
        oldUser.setActive(true);

        GroupDiscussionInfo groupDiscussionInfo = new GroupDiscussionInfo();
        groupDiscussionInfo.setId(1);
        groupDiscussionInfo.setTitle("g1");

        GroupSub groupFromDb = new GroupSub();
        groupFromDb.setId(groupDiscussionInfo.getId());
        groupFromDb.setTitle(groupDiscussionInfo.getTitle());
        groupFromDb.addUser(oldUser);

        Mockito.when(groupSubRepository.findById(groupDiscussionInfo.getId())).thenReturn(Optional.of(groupFromDb));

        GroupSub expectedGroupSub = new GroupSub();
        expectedGroupSub.setId(groupDiscussionInfo.getId());
        expectedGroupSub.setTitle(groupDiscussionInfo.getTitle());
        expectedGroupSub.addUser(oldUser);
        expectedGroupSub.addUser(newUser);

        //when
        groupSubService.save(CHAT_ID, groupDiscussionInfo);

        //then
        Mockito.verify(groupSubRepository).findById(groupDiscussionInfo.getId());
        Mockito.verify(groupSubRepository).save(expectedGroupSub);
    }
}
