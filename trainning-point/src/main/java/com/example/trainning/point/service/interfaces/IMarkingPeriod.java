package com.example.trainning.point.service.interfaces;

import com.example.trainning.point.dto.request.evalution.periodmarking.MarkingPeriodRequest;
import com.example.trainning.point.dto.response.periodmarking.MarkingPeriodResponse;

public interface IMarkingPeriod {
    MarkingPeriodResponse createMarkingPeriod (MarkingPeriodRequest request);
}
