package org.example.dao;

import org.example.bean.Team;

public interface TeamDAO {
    public int insertTeam(Team team);
    public int deleteTeamByName(String name);

}
