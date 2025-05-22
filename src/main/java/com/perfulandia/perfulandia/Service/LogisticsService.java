package com.perfulandia.perfulandia.Service;
import com.perfulandia.perfulandia.Model.Logistics;
import com.perfulandia.perfulandia.Repository.LogisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class LogisticsService {

    @Autowired
    private LogisticsRepository logisticsRepository;

    public Optional<Logistics> getLogisticsById(int id) {
        return logisticsRepository.findById(id);
    }

    public Logistics saveLogistics(Logistics logistics) {
        return logisticsRepository.save(logistics);
    }

    public void deleteLogistics(int id) {
        logisticsRepository.deleteById(id);
    }

    public List<Logistics> getAllLogistics() {
    }
}
