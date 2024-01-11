package org.example.untitled2.dbUtils;

import org.example.untitled2.beans.AreaCheckerBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface HitRepository extends JpaRepository<AreaCheckerBean, Long> {
    void deleteAllByOwnerid(int ownerid);

    Collection<AreaCheckerBean> findAllByOwnerid(int ownerid);
}
