package com.github.jrtb.javarushtelegrambot.javarushclient.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Builder
@Getter
public class GroupRequestArgs {

    private final String query;

    private final GroupInfoType type;

    private final GroupFilter filter;

    private final Integer offSet;

    private final Integer limit;

    public Map populateQueries() {

        Map queries = new HashMap<>();

        if (nonNull(query)) queries.put("query", query);

        if (nonNull(type)) queries.put("type", type);

        if (nonNull(filter)) queries.put("filter", filter);

        if (nonNull(offSet)) queries.put("offset", offSet);

        if (nonNull(limit)) queries.put("limit", limit);

        return queries;
    }

}
