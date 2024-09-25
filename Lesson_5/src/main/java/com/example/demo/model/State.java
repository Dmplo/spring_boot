package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("state")
public enum State {
    NOT_STARTED, IN_PROGRESS, COMPLETED;
}
