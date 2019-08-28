package com.company.levelupservice.dao;

import com.company.levelupservice.model.LevelUp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LevelUpDaoJdbcTemplateImplTest {

    @Autowired
    LevelUpDao dao;

    @Before
    public void setUp() throws Exception {
        List<LevelUp> levelUps = dao.getAllLevelUps();
        for (LevelUp each : levelUps) {
            dao.deleteLevelUp(each.getId());
        }
    }

    @Test
    public void addGetGetAllDeleteLevelUp() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setPoints(25);
        levelUp.setMemberDate(LocalDate.of(2012, 12, 20));

        levelUp = dao.addLevelUp(levelUp);

        LevelUp levelUp1 = dao.getLevelUp(levelUp.getId());
        assertEquals(levelUp, levelUp1);

        List<LevelUp> levelUps = dao.getAllLevelUps();
        assertEquals(levelUps.size(), 1);


        dao.deleteLevelUp(levelUp.getId());
        levelUp1 = dao.getLevelUp(levelUp.getId());
        assertNull(levelUp1);
    }


    @Test
    public void updateLevelUp() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setPoints(25);
        levelUp.setMemberDate(LocalDate.of(2012, 12, 20));

        levelUp = dao.addLevelUp(levelUp);

        levelUp.setPoints(30);
        dao.updateLevelUp(levelUp);

        LevelUp levelUp1 = dao.getLevelUp(levelUp.getId());
        assertEquals(levelUp, levelUp1);
    }

}