package com.example.dbeaver.compare_events.entity;

public record ResponseLinks(
        Link self,
        Link next,
        Link first,
        Link prev
) {
}
