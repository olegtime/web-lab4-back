package org.example.untitled2.beans;

import org.example.untitled2.dbUtils.HitService;
import org.example.untitled2.utils.AreaChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

@RestController
@RequestMapping("/api/data")
public class ResultManager {

    private LinkedList<AreaCheckerBean> results = new LinkedList<>();

    private final HitService hitService;

    @Autowired
    public ResultManager(HitService hitService) {
        this.hitService = hitService;
    }

    @GetMapping("/results")
    public LinkedList<AreaCheckerBean> getResults() {
        return results;
    }

    @PostMapping("/get")
    public ResponseEntity<LinkedList<AreaCheckerBean>> getStringResults(@RequestParam("owner_id") int owner_id) {
        System.out.println("Get results for user with id = " + owner_id);

        results = new LinkedList<>(hitService.getAllHitsByOwner(owner_id));
        return ResponseEntity.ok(results);

    }

    @Transactional
    @PostMapping("/add")
    public ResponseEntity<AreaCheckerBean> addResults(@RequestParam("x") double x, @RequestParam("y") double y,
                                                      @RequestParam("r") double r, @RequestParam("owner_id") int owner_id) {
        System.out.println("Add results for user with id = " + owner_id);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        String requestTime = dateFormat.format(new Date(System.currentTimeMillis()));
        long startTime = System.nanoTime();

        Hit hit = new Hit(x, y, r);
        AreaCheckerBean currentResult = new AreaCheckerBean();

        results = new LinkedList<>(hitService.getAllHitsByOwner(owner_id));
        operateHit(requestTime, startTime, currentResult, hit, owner_id);

        return ResponseEntity.ok(currentResult);

    }

    private void operateHit(String requestTime, long startTime, AreaCheckerBean currentResult, Hit hit, int owner_id) {
        currentResult.setX(hit.getX());
        currentResult.setY(hit.getY());
        currentResult.setR(hit.getR());
        currentResult.setOwnerid(owner_id);
        currentResult.setStatus(AreaChecker.isHit(hit.getX(), hit.getY(), hit.getR()));
        currentResult.setRequestTime(requestTime);
        currentResult.setScriptTime(System.nanoTime() - startTime);

        System.out.println(currentResult);

        hitService.addHit(currentResult, owner_id);
        results.addFirst(currentResult);

    }

    @Transactional
    @PostMapping("/clear")
    public ResponseEntity<Object> clearUserResults(@RequestParam("owner_id") int owner_id) {
        System.out.println("Clear results for user with id = " + owner_id);

        hitService.removeAllByOwner(owner_id);
        results.clear();

        return ResponseEntity.ok().build();

    }
}
