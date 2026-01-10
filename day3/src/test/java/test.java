import org.example.utils.SpringUtil;
import org.example.bean.Team;
import org.example.service.TeamService;

public class test {
    public static void main(String[] args) {
        SpringUtil.getContext().getBean(TeamService.class).initTeams();
        SpringUtil.getContext().getBean(TeamService.class).insertTeam(new Team("张三","南京"));
        SpringUtil.getContext().getBean(TeamService.class).insertTeam(new Team("中超","北京"));
        SpringUtil.getContext().getBean(TeamService.class).deleteTeamByName("中超");

    }
}
