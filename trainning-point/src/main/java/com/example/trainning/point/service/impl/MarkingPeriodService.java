package com.example.trainning.point.service.impl;

import com.example.trainning.point.dto.request.evalution.periodmarking.MarkingPeriodRequest;
import com.example.trainning.point.dto.response.periodmarking.MarkingPeriodResponse;
import com.example.trainning.point.entity.MarkingPeriod;
import com.example.trainning.point.mapper.custom.MarkingPeriodMapper;
import com.example.trainning.point.repository.IMarkingPeriodRepository;
import com.example.trainning.point.service.interfaces.IMarkingPeriod;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class MarkingPeriodService implements IMarkingPeriod {
    IMarkingPeriodRepository markingPeriodRepository;
    MarkingPeriodMapper markingPeriodMapper;

    @Override
    public MarkingPeriodResponse createMarkingPeriod(MarkingPeriodRequest request) {
        MarkingPeriod entity = markingPeriodMapper.convertToMarkingPeriod(request);
        return markingPeriodMapper.converToMarkingPeriodResponse(markingPeriodRepository.save(entity));
    }

    public List<MarkingPeriodResponse> getAllMarkingPeriod(){
        return markingPeriodRepository.findAll().stream().map(markingPeriodMapper::converToMarkingPeriodResponse).toList();
    }
}
