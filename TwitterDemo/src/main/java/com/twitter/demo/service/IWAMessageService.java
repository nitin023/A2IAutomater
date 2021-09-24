package com.twitter.demo.service;

import com.twitter.demo.DTO.BookedLeadsDto;

public interface IWAMessageService {

    void sendWAMessage (BookedLeadsDto bookedLeadsDto);
}
