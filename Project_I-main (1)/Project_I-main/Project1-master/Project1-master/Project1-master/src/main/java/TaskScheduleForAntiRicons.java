import com.google.gson.JsonObject;
import template.service.GroupServiceImpl;
import template.jsonUtil.JsonTool;
import template.service.airtable.Table;
import template.team_config.Config;

import java.util.List;

public class TaskScheduleForAntiRicons {
    public static void main(String[] args) throws Exception{
        String configAirTable = "D:\\java prj1\\Project1-master\\Project1-master\\Project1-master\\src\\main\\java\\template\\accessInfo\\configAirTable.json";
        //Change the value of variable infoTaskSchedule to absolute path of infoTaskSchedule.json and start using
        String infoTaskSchedule = "D:\\java prj1\\Project1-master\\Project1-master\\Project1-master\\src\\main\\java\\infoTaskSchedule.json";
        
        String token = Config.getAccessToken();
        String tableId = JsonTool.getAccessInfo(infoTaskSchedule).get("tableId").getAsString();
        String groupId = JsonTool.getAccessInfo(infoTaskSchedule).get("groupId").getAsString();

        List<JsonObject> fields = GroupServiceImpl.listUsersAsJson(groupId, token);
        //token Airtable
        JsonObject access = JsonTool.getAccessInfo(configAirTable).getAsJsonObject();
        String personal_access_token = access.get("personal_access_token").getAsString();
        String baseId = access.get("baseId").getAsString();

        JsonObject fieldTable = Table.getTable(tableId, baseId, personal_access_token);

        Table table = new Table(fieldTable, baseId, personal_access_token);
        table.pullAllRecords(fields, baseId, personal_access_token);

    }


}
