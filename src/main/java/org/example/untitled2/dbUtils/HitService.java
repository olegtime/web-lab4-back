package org.example.untitled2.dbUtils;

import org.example.untitled2.beans.AreaCheckerBean;

import java.security.Principal;
import java.util.Collection;

public interface HitService {

    void addHit(AreaCheckerBean hit, int ownerid);

    void removeAllByOwner(int ownerid);

    Collection<AreaCheckerBean> getAllHitsByOwner(int ownerid);
}
