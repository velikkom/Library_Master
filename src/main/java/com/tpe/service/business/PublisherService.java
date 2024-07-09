package com.tpe.service.business;

import com.tpe.entity.business.Publisher;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.payload.messages.ErrorMessages;
import com.tpe.payload.request.business.PublisherRequest;
import com.tpe.payload.response.business.PublisherResponse;
import com.tpe.repository.business.PublisherRepository;
import com.tpe.service.helper.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherService
{
    private final PublisherRepository publisherRepository;

    public Publisher savePublisher(Publisher publisher)
    {
        ValidationUtil.validateName(publisher.getName());
        Optional<Publisher> existingPublisher = publisherRepository.findByName(publisher.getName());
        ValidationUtil.checkIfExists(existingPublisher,"Publisher");
        return publisherRepository.save(publisher);
    }

}
