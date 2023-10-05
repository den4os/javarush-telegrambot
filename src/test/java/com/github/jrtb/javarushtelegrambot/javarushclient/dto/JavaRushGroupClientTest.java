package com.github.jrtb.javarushtelegrambot.javarushclient.dto;

import com.github.jrtb.javarushtelegrambot.javarushclient.JavaRushGroupClient;
import com.github.jrtb.javarushtelegrambot.javarushclient.JavaRushGroupClientImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Integration-level testing for JavaRushGroupClientImplTest")
class JavaRushGroupClientTest {

    private final JavaRushGroupClient javaRushGroupClient = new JavaRushGroupClientImpl("https://javarush.com/api/1.0/rest");

    @Test
    public void shouldProperlyGetGroupsWithEmptyArgs() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        //when
        List<GroupInfo> groupInfoList = javaRushGroupClient.getGroupList(args);

        //then
        Assertions.assertNotNull(groupInfoList);
        Assertions.assertFalse(groupInfoList.isEmpty());
    }

    @Test
    public void shouldProperlyGetWithOffSetAndLimit() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder()
                .offSet(1)
                .limit(3)
                .build();

        //when
        List<GroupInfo> groupList = javaRushGroupClient.getGroupList(args);

        //then
        Assertions.assertNotNull(groupList);
        Assertions.assertEquals(3, groupList.size());
    }

    @Test
    public void shouldProperlyGetGroupsDiscWithEmptyArgs() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        //when
        List<GroupDiscussionInfo> groupList = javaRushGroupClient.getGroupDiscussionList(args);

        //then
        Assertions.assertNotNull(groupList);
        Assertions.assertFalse(groupList.isEmpty());
    }

    @Test
    public void shouldProperlyGetGroupDiscWithOffSetAndLimit() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder()
                .offSet(1)
                .limit(3)
                .build();

        //when
        List<GroupDiscussionInfo> groupList = javaRushGroupClient.getGroupDiscussionList(args);

        //then
        Assertions.assertNotNull(groupList);
        Assertions.assertEquals(3, groupList.size());
    }

    @Test
    public void shouldProperlyGetGroupCount() {
        //this test works only with Unauthorized user
        //given
        GroupsCountRequestArgs args = GroupsCountRequestArgs.builder().build();

        //when
        Integer groupCount = javaRushGroupClient.getGroupCount(args);

        //then
        Assertions.assertEquals(27, groupCount);
    }

    @Test
    public void shouldProperlyGetGroupTECHCount() {
        //given
        GroupsCountRequestArgs args = GroupsCountRequestArgs.builder()
                .type(GroupInfoType.TECH)
                .build();

        //when
        Integer groupCount = javaRushGroupClient.getGroupCount(args);

        //then
        Assertions.assertEquals(7, groupCount);
    }

    @Test
    public void shouldProperlyGetGroupById() {
        //given
        Integer androidGroupId = 16;

        //when
        GroupDiscussionInfo groupById = javaRushGroupClient.getGroupById(androidGroupId);

        //then
        Assertions.assertNotNull(groupById);
        Assertions.assertEquals(GroupInfoType.TECH, groupById.getType());
        Assertions.assertEquals(16, groupById.getId());
        Assertions.assertEquals("android", groupById.getKey());
    }
}
