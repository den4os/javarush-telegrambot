package com.github.jrtb.javarushtelegrambot.javarushclient.dto;


import lombok.Data;

@Data
public class MeGroupInfo {

    private MeGroupInfoStatus status;

    private Integer userGroupId;

}
