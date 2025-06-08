package com.example.trainning.point.mapper.custom;

import com.example.trainning.point.dto.request.evalution.standard.EvaluationStandardDetailRequest;
import com.example.trainning.point.dto.response.evalution.standard.EvalutionStandardDetailResponse;
import com.example.trainning.point.entity.EvaluationStandardDetail;
import com.example.trainning.point.entity.EvalutionStandard;
import com.example.trainning.point.exception.AppException;
import com.example.trainning.point.exception.ErrorCode;
import com.example.trainning.point.repository.IEvalutionStandardRepository;
import com.example.trainning.point.service.impl.EvalutionStandardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class EvaluationStandardDetailMapper {

    IEvalutionStandardRepository evalutionStandardRepository;

    public EvaluationStandardDetail convertToEsDetail (EvaluationStandardDetailRequest request){
        log.info("stop here");
        EvalutionStandard evalutionStandard = evalutionStandardRepository.findById(request.getEvaluationStandardId()).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        log.info("stop right here");
        return EvaluationStandardDetail.builder()
                .esDetailName(request.getEsDetailName())
                .evalutionStandard(evalutionStandard)
                .minPoint(request.getMinPoint())
                .maxPoint(request.getMaxPoint())
                .build();
    }

    public EvalutionStandardDetailResponse convertToEsResponse (EvaluationStandardDetail entity) {
        return EvalutionStandardDetailResponse.builder()
                .esDetailName(entity.getEsDetailName())
                .evaluationStandardId(entity.getEvalutionStandard().getId())
                .evaluationStandardName(entity.getEvalutionStandard().getName())
                .esDetailId(entity.getEsDetailId())
                .maxPoint(entity.getMaxPoint())
                .minPoint(entity.getMinPoint())
                .build();
    }
}
