package com.twitter.demo.Services;

import com.twitter.demo.DTO.BookedLeadsDto;

public interface IWAMessageService {

    void sendWAMessage (BookedLeadsDto bookedLeadsDto);
}
