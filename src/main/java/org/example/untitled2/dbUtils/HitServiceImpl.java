package org.example.untitled2.dbUtils;

import org.example.untitled2.beans.AreaCheckerBean;
import org.example.untitled2.beans.Hit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Service
public class HitServiceImpl implements HitService {

    private final HitRepository hitRepository;

    @Autowired
    public HitServiceImpl(HitRepository hitRepository) {
        this.hitRepository = hitRepository;
    }

    @Override
    public void addHit(AreaCheckerBean hit, int ownerid) {
        hitRepository.save(hit);
    }

    @Override
    public void removeAllByOwner(int ownerid) {
        hitRepository.deleteAllByOwnerid(ownerid);
    }

    @Override
    public Collection<AreaCheckerBean> getAllHitsByOwner(int ownerid) {
        return hitRepository.findAllByOwnerid(ownerid);
    }
}