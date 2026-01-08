package dao;

import bean.Team;

import java.util.List;

public interface TeamDAO {
    public int insertTeam(Team team);
    public int deleteTeamByName(String name);

}
