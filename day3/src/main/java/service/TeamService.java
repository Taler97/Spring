package service;

import bean.Team;
import dao.TeamDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TeamService implements TeamDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TeamService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertTeam(Team team) {
        String sql = "INSERT INTO team (name, address) VALUES (?, ?)";
        System.out.println("已成功插入"+team);
        return jdbcTemplate.update(sql, team.getName(), team.getAddress());
    }

    @Override
    public int deleteTeamByName(String name) {
        String sql = "DELETE FROM team WHERE name = ?";
        System.out.println("已经成功删除名为："+name+"的Team");
        return jdbcTemplate.update(sql,name);
    }

    public void initTeams() {
        try {
            // 检查表是否存在
            boolean tableExists = checkTableExists("team");

            if (tableExists) {
                System.out.println("检测到 team 表已存在，删除并重建...");
                // 删除表
                jdbcTemplate.execute("DROP TABLE team");
                System.out.println("✓ 旧表删除成功");
            } else {
                System.out.println("team 表不存在，直接创建...");
            }

            jdbcTemplate.execute( "CREATE TABLE IF NOT EXISTS team (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "address VARCHAR(100)" +
                    ")");
            System.out.println("建表成功");
        } catch (Exception e) {
            System.err.println("建表失败: " + e.getMessage());
            throw e;
        }

    }
    private boolean checkTableExists(String tableName) {
        try {
            // 查询 information_schema 判断表是否存在
            String sql = "SELECT COUNT(*) FROM information_schema.tables " +
                    "WHERE table_schema = DATABASE() AND table_name = ?";
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, tableName);
            return count != null && count > 0;
        } catch (Exception e) {
            return false;
        }
    }
}