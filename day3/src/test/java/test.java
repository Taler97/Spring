import Utils.SpringUtil;
import bean.Team;
import com.alibaba.druid.util.StringUtils;
import service.TeamService;

public class test {
    public static void main(String[] args) {
        SpringUtil.getContext().getBean(TeamService.class).initTeams();
        SpringUtil.getContext().getBean(TeamService.class).insertTeam(new Team("张三","南京"));
        SpringUtil.getContext().getBean(TeamService.class).insertTeam(new Team("中超","北京"));
        SpringUtil.getContext().getBean(TeamService.class).deleteTeamByName("中超");

    }
}
