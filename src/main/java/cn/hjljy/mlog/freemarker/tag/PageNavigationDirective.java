package cn.hjljy.mlog.freemarker.tag;

import cn.hjljy.mlog.common.constants.Constant;
import cn.hjljy.mlog.entity.MlogPageNavigation;
import cn.hjljy.mlog.service.IMlogPageNavigationService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Freemarker custom tag of menu.
 *
 * @author ryanwang
 * @date 2019-03-22
 */
@Component
public class PageNavigationDirective implements TemplateDirectiveModel {


    private final IMlogPageNavigationService pageNavigationService;

    public PageNavigationDirective(Configuration configuration, IMlogPageNavigationService pageNavigationService) {
        this.pageNavigationService = pageNavigationService;
        configuration.setSharedVariable("menuTag",this);
        configuration.setSharedVariable("pageNavigationTag",this);
    }

//    private static final String METHOD_KEY = "method";
//
//    private final MenuService menuService;
//
//    private final OptionService optionService;

//    public MenuTagDirective(Configuration configuration, MenuService menuService,
//        OptionService optionService) {
//        this.menuService = menuService;
//        this.optionService = optionService;
//        configuration.setSharedVariable("menuTag", this);
//    }

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
        TemplateDirectiveBody body) throws TemplateException, IOException {
        final DefaultObjectWrapperBuilder builder =
            new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);

        if (params.containsKey(Constant.METHOD_KEY)) {
            String method = params.get(Constant.METHOD_KEY).toString();
            switch (method) {
                case "list":
                    env.setVariable("menus", builder.build()
                        .wrap(pageNavigationService.list(new LambdaQueryWrapper<MlogPageNavigation>().orderByDesc(MlogPageNavigation::getPriority))));
                    break;
//                case "tree":
//                    String treeTeam = optionService
//                        .getByPropertyOrDefault(PrimaryProperties.DEFAULT_MENU_TEAM, String.class,
//                            "");
//                    env.setVariable("menus", builder.build()
//                        .wrap(menuService.listByTeamAsTree(treeTeam, Sort.by(DESC, "priority"))));
//                    break;
//                case "listTeams":
//                    env.setVariable("teams",
//                        builder.build().wrap(menuService.listTeamVos(Sort.by(DESC, "priority"))));
//                    break;
//                case "listByTeam":
//                    String team = params.get("team").toString();
//                    env.setVariable("menus", builder.build()
//                        .wrap(menuService.listByTeam(team, Sort.by(DESC, "priority"))));
//                    break;
//                case "treeByTeam":
//                    String treeTeamParam = params.get("team").toString();
//                    env.setVariable("menus", builder.build().wrap(
//                        menuService.listByTeamAsTree(treeTeamParam, Sort.by(DESC, "priority"))));
//                    break;
//                case "count":
//                    env.setVariable("count", builder.build().wrap(menuService.count()));
//                    break;
                default:
                    break;
            }
        }
        body.render(env.getOut());
    }
}
