package com.example.dbeaver.compare_events.entity;

public record ResponseTag(
        int _page,
        ResponseLinks _links,
        EmbeddedTags _embedded
) {
}
