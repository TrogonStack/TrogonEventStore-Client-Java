package io.trogonstack.eventstore.client;

interface WorkItem {
    void accept(WorkItemArgs args, Exception error);
}