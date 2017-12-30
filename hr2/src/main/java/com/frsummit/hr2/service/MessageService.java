package com.frsummit.hr2.service;

import com.frsummit.hr2.model.Message;

import java.util.List;

public interface MessageService {
    public void saveMessage(Message message);
    public List<Message> findMessage();
    public List<Message> findMyALLMessage();
    public List<Message> loadALLMessage();
}
