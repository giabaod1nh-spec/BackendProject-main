package com.example.trainning.point.service.impl;

import com.example.trainning.point.dto.request.evalution.result.EvalutionResultRequest;
import com.example.trainning.point.dto.request.semester.SemesterRequest;
import com.example.trainning.point.dto.response.evalution.result.EvalutionResultResponse;
import com.example.trainning.point.dto.response.evalution.result.EvalutionResultWithSemester;
import com.example.trainning.point.dto.response.semester.SemesterResponse;
import com.example.trainning.point.entity.EvalutionResult;
import com.example.trainning.point.entity.Semester;
import com.example.trainning.point.exception.AppException;
import com.example.trainning.point.exception.ErrorCode;
import com.example.trainning.point.mapper.custom.EvalutionResultMapper;
import com.example.trainning.point.mapper.custom.SemesterMapper;
import com.example.trainning.point.repository.IEvalutionResultRepository;
import com.example.trainning.point.repository.ISemesterRepository;
import com.example.trainning.point.service.interfaces.IEvalutionResultService;
import com.example.trainning.point.service.interfaces.ISemesterService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EvalutionResultService implements IEvalutionResultService {
    IEvalutionResultRepository evalutionResultRepository;
    EvalutionResultMapper resultMapper;

    @Override
    public EvalutionResult findEntityById(Long id) {
        var entity = evalutionResultRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        if (entity.getIsDelete() == true)
            throw new AppException(ErrorCode.NOT_FOUND);
        return entity;
    }

    @Override
    public EvalutionResultResponse findById(Long id) {
        var entity = this.findEntityById(id);
        return resultMapper.convertToResponse(entity);
    }

    @Override
    public List<EvalutionResultResponse> findAll() {
        return evalutionResultRepository.findAll().stream().map(it -> resultMapper.convertToResponse(it)).toList();
    }

    @Override
    public EvalutionResultResponse create(EvalutionResultRequest request, String userId) {
        var entity = resultMapper.convertToEntity(request, userId);
        return resultMapper.convertToResponse(evalutionResultRepository.save(entity));
    }

    @Override
    public EvalutionResultResponse update(Long id, EvalutionResultRequest request) {
        var entity = this.findEntityById(id);
        entity.setStatus(request.getStatus());

        return resultMapper.convertToResponse(evalutionResultRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        var entity = this.findEntityById(id);
        entity.setIsDelete(true);
        evalutionResultRepository.save(entity);
    }

    @Override
    public EvalutionResult findBySemesterAndUser(Long idSemester, String userId) {
        return evalutionResultRepository.findBySemesterIdAndUserId(idSemester, userId);
    }

    @Override
    public  EvalutionResult findByUser(String userId){
        return evalutionResultRepository.findByUser(userId);
    }

    @Override
    public List<EvalutionResultWithSemester> findResultAllSemester(String userId){
        List<EvalutionResult> results = evalutionResultRepository.findResultAllSemester(userId);
        return  results.stream()
                .map(result -> resultMapper.convertToAllResult(result)).toList();
    }

    //Manage ADMIN Dashboard API for evauation result statistic
    @Override
    public Long countStudentHaveExcellentStatus(Long semesterId){
        return evalutionResultRepository.countByStudentHaveExcellentStatus(semesterId);
    }

    @Override
    public Long countStudentHaveVeryGoodStatus(Long semesterId){
        return evalutionResultRepository.countByStudentHaveVeryGoodStatus(semesterId);
    }

    @Override
    public Long countStudentHaveGoodStatus(Long semesterId){
        return evalutionResultRepository.countByStudentHaveGoodStatus(semesterId);
    }

    @Override
    public Long countStudentHaveAverageStatus(Long semesterId){
        return evalutionResultRepository.countByStudentHaveAverageStatus(semesterId);
    }

    @Override
    public Long countStudentHaveBelowAverageStatus(Long semesterId){
        return evalutionResultRepository.countByStudentHaveBelowAverageStatus(semesterId);
    }

    @Override
    public Long countStudentHavePoorStatus(Long semesterId){
        return evalutionResultRepository.countByStudentHavePoorStatus(semesterId);
    }

    //Manage ADMIN Dashboard API for evauation result statistic per class

    @Override
    public Long countExcellentPerClass(Long semesterId , Long classId){
        return evalutionResultRepository.countByExcellentPerClass(semesterId , classId);
    }

    @Override
    public Long countVeryGoodPerClass(Long semesterId , Long classId){
        return evalutionResultRepository.countByVeryGoodPerClass(semesterId , classId);
    }

    @Override
    public Long countGoodPerClass(Long semesterId , Long classId){
        return evalutionResultRepository.countByGoodPerClass(semesterId , classId);
    }

    @Override
    public Long countAveragePerClass(Long semesterId , Long classId){
        return evalutionResultRepository.countByAveragePerClass(semesterId , classId);
    }

    @Override
    public Long countBelowAveragePerClass(Long semesterId , Long classId){
        return evalutionResultRepository.countByBelowAveragePerClass(semesterId , classId);
    }

    @Override
    public Long countPoorPerClass(Long semesterId , Long classId){
        return evalutionResultRepository.countByPoorPerClass(semesterId , classId);
    }

    //@Override
    //public List<EvalutionResultResponse> getResultPerClassSemester(Long classId, Long semesterId) {
       // return  evalutionResultRepository.findResultPerClassSemester(classId, semesterId).stream().map(ev -> resultMapper.convertToAllResult(ev)).toList();
   // }
}
