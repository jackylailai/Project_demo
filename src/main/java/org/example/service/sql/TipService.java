package org.example.service.sql;

import org.example.entity.Tip;
import org.example.repository.TipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipService{
    private final TipRepository tipRepository;

    @Autowired
    public TipService(TipRepository tipRepository) {
        this.tipRepository = tipRepository;
    }

    public Tip getTipById(Long id) {
        return tipRepository.findById(id).orElse(null);
    }


    public List<Tip> getAllTips() {
        return tipRepository.findAll();
    }


    public void createTip(Tip tip) {
        tipRepository.save(tip);
    }


    public void updateTip(Tip tip) {
        tipRepository.save(tip);
    }


    public void deleteTip(Long id) {
        tipRepository.deleteById(id);
    }


    @Transactional
    public int updateTipTitleById(String title, Long id) {
        return tipRepository.updateById(title, id);
    }

    public Tip getTipByTipId(Long tipId) {
        return tipRepository.findByTipId(tipId);
    }

    public List<String> getTipTitlesByUnitId(Long unitId) {
        return tipRepository.findTitlesByUnitId(unitId);
    }


    public Tip saveTip(Tip tip) {
        return tipRepository.save(tip);
    }
}
