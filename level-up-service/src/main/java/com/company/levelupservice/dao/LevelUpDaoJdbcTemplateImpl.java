package com.company.levelupservice.dao;

import com.company.levelupservice.model.LevelUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LevelUpDaoJdbcTemplateImpl implements LevelUpDao {


    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_LEVEL_UP_SQL =
            "insert into level_up (customer_id, points, member_date) values (?, ?, ?)";

    private static final String SELECT_LEVEL_UP_SQL =
            "select * from level_up where level_up_id = ?";

    private static final String SELECT_ALL_LEVEL_UPS_SQL =
            "select * from level_up";

    private static final String UPDATE_LEVEL_UP_SQL =
            "update level_up set customer_id = ?, points = ?, member_date = ? where level_up_id = ?";

    private static final String DELETE_LEVEL_UP =
            "delete from level_up where level_up_id = ?";

    private static final String SELECT_LEVEL_UP_BY_CUSTOMER =
            "select * from level_up where customer_id =? ";

    private static final String SELECT_TOTAL_POINTS_FROM_LEVEL_BY_CUSTOMER =
            "select sum(points) from level_up where customer_id = ?";

    @Autowired
    public LevelUpDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int getTotalLevelUpPointsByCustomerId(int customerId) {
       int points = jdbcTemplate.queryForObject(SELECT_TOTAL_POINTS_FROM_LEVEL_BY_CUSTOMER, Integer.class, customerId);

        return points;
    }

    @Override
    @Transactional
    public LevelUp addLevelUp(LevelUp levelUp) {
        jdbcTemplate.update(
                INSERT_LEVEL_UP_SQL,
                levelUp.getCustomerId(),
                levelUp.getPoints(),
                levelUp.getMemberDate());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        levelUp.setId(id);

        return levelUp;    }

    @Override
    public LevelUp getLevelUp(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_LEVEL_UP_SQL, this::mapRowToLevelUp, id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this
            return null;
        }    }

    @Override
    public List<LevelUp> getAllLevelUps() {
        return jdbcTemplate.query(SELECT_ALL_LEVEL_UPS_SQL, this::mapRowToLevelUp);    }


    @Override
    public void updateLevelUp(LevelUp levelUp) {
        jdbcTemplate.update(
                UPDATE_LEVEL_UP_SQL,
                levelUp.getCustomerId(),
                levelUp.getPoints(),
                levelUp.getMemberDate(),
                levelUp.getId());

    }

    @Override
    public void deleteLevelUp(int id) {
        jdbcTemplate.update(DELETE_LEVEL_UP, id);
    }

    @Override
    public List<LevelUp> getLevelUpByCustomerId(int customerId) {
        try {
            return jdbcTemplate.query(SELECT_LEVEL_UP_BY_CUSTOMER, this::mapRowToLevelUp, customerId);
        }catch (EmptyResultDataAccessException e) {
            // if there is no match for this invoice id, return null
            return null;
        }
    }

    private LevelUp mapRowToLevelUp(ResultSet rs, int rowNum) throws SQLException {
        LevelUp levelUp = new LevelUp();
        levelUp.setId(rs.getInt("level_up_id"));
        levelUp.setCustomerId(rs.getInt("customer_id"));
        levelUp.setPoints(rs.getInt("points"));
        levelUp.setMemberDate(rs.getDate("member_date").toLocalDate());

        return levelUp;
    }
}
