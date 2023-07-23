import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import template.jsonUtil.JsonTool;
import template.persistence.dto.Group;
import template.persistence.dto.User;
import template.service.*;

import template.colorUtil.Color;
import template.service.airtable.Table;

import template.team_config.Config;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Scanner;

public class CLI {

    public static void showMenu()
    {
        //Change the value of configAirTable and Config to absolute path of configAirTable.json and Config.json respectively
        String configAirTable = "D:\\java prj1\\Project1-master\\Project1-master\\Project1-master\\src\\main\\java\\template\\accessInfo\\configAirTable.json";


        String general = "----------------------------------------------\n" +
                "            ANTI-RICONS Application\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣤⣤⣤⣀⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⠟⠉⠉⠉⠉⠉⠉⠉⠙⠻⢶⣄⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⠙⣷⡀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⡟⠀⣠⣶⠛⠛⠛⠛⠛⠛⠳⣦⡀⠀⠘⣿⡄⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⠁⠀⢹⣿⣦⣀⣀⣀⣀⣀⣠⣼⡇⠀⠀⠸⣷⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡏⠀⠀⠀⠉⠛⠿⠿⠿⠿⠛⠋⠁⠀⠀⠀⠀⣿\n" +
                "⠀⠀      ⢠⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡇⠀\n" +
                "        ⣸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣧⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⢸⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣾⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⣿⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀⠀⣿⠀\n" +
                "⠀⠀⠀⠀⠀⠀⢰⣿⠀⠀⠀⠀⣠⡶⠶⠿⠿⠿⠿⢷⣦⠀⠀⠀⠀⠀    ⣿⠀\n" +
                "⠀⠀⣀⣀⣀⠀⣸⡇⠀⠀⠀⠀⣿⡀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀  ⣿⠀\n" +
                "⣠⡿⠛⠛⠛⠛⠻⠀⠀⠀⠀⠀⢸⣇⠀⠀⠀⠀⠀⠀⣿⠇⠀⠀⠀⠀⠀ ⠀ ⣿⠀\n" +
                "⢻⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⡟⠀⠀⢀⣤⣤⣴⣿⠀⠀⠀⠀⠀⠀  ⠀⣿⠀\n" +
                "⠈⠙⢷⣶⣦⣤⣤⣤⣴⣶⣾⠿⠛⠁⢀⣶⡟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡟⠀\n" +
                "                ⠈⣿⣆⡀⠀⠀⠀⠀⠀⠀⢀⣠⣴⡾⠃⠀\n" +
                "⠀                ⠈⠛⠻⢿⣿⣾⣿⡿⠿⠟⠋⠁⠀⠀⠀\n" +
                "How can I help you? Please choose an option:\n" +
                "--MS Teams--\n" +
                "1. Create user\n" +
                "2. Create multiple users from csv file\n" +
                "3. Delete user from organization\n" +
                "4. Create Group\n" +
                "5. Create Team\n" +
                "6. Delete Team(Group)\n" +
                "7. Create Channel\n" +
                "8. Add user/multiple users to Team\n" +
                "9. Get user by principal name\n" +
                "10. Assign license for user\n" +
                "11. List existed groupIDs\n" +

                "--Airtable--\n" +
                "12. Synchronize Users information in an organization to Airtable \n" +
                "13. Synchronize Users information in a Team to Airtable\n" +
                "14. Write to XLSX file\n" +
                "----------------------------------------------\n" +
                "15. Print help\n" +
                "16. Exit";
        try {
            while (true) {
                Color.printGreen(general);
                Scanner input = new Scanner(System.in);
                String option = input.nextLine();

                UserServiceImpl service = new UserServiceImpl();
                GroupServiceImpl groupService = new GroupServiceImpl();

                if (option.equals("1")) {

                    Color.printYellow("-- Create user -- ");

                    Color.printYellow("Enter displayName: ");
                    String displayName = input.nextLine();

                    Color.printYellow("Enter mailNickname: ");
                    String mailNickname = input.nextLine();

                    Color.printYellow("Enter userPrincipalName: ");
                    String userPrincipalName = input.nextLine();

                    Color.printYellow("Enter password: ");
                    String password = input.nextLine();

                    User user = new User(displayName, mailNickname, userPrincipalName, password);
                    service.createUser(user);
                } else if (option.equals("2")) {

                    Color.printYellow("-- Create multiple users from csv file --");

                    Color.printYellowNo("Enter filepath: ");
                    String path = input.nextLine();

                    service.createAllUsers(path);
                } else if (option.equals("3")) {
                    Color.printYellow("-- Delete user from organization --");

                    Color.printYellowNo("Enter userId: ");
                    String userId = input.nextLine();

                    service.deleteUser(userId);
                } else if (option.equals("4")) {
                    Color.printYellow("-- Create Group --");

                    Color.printYellowNo("Enter displayName: ");
                    String displayName = input.nextLine();

                    Color.printYellowNo("Enter description: ");
                    String description = input.nextLine();

                    Color.printYellowNo("Enter mailNickname: ");
                    String mailNick = input.nextLine();

                    Color.printYellowNo("Enter ownerId: ");
                    String ownerId = input.nextLine();

                    Color.printYellowNo("Enter userIds: ");
                    List<String> userId = new ArrayList<>();
                    String line = input.nextLine();
                    while (!line.isEmpty()) {
                        userId.add(line);
                        line = input.nextLine();
                    }

                    Group group = new Group(displayName, mailNick, ownerId);

                    String groupId = groupService.createGroup(group, ownerId, userId);

                    groupService.createTeam(groupId);
                } else if (option.equals("5")) {
                    Color.printYellow("-- Create Team --");

                    Color.printYellowNo("Enter groupId: ");
                    String groupId = input.nextLine();

                    groupService.createTeam(groupId);
                } else if (option.equals("6")) {
                    Color.printYellow("-- Delete Team(Group) --");

                    Color.printYellowNo("Enter groupId: ");
                    String groupId = input.nextLine();

                    groupService.deleteTeam(groupId);
                } else if (option.equals("7")) {
                    Color.printYellow("-- Create Channel --");

                    Color.printYellowNo("Enter groupId: ");
                    String groupId = input.nextLine();

                    Color.printYellowNo("Enter displayName: ");
                    String displayName = input.nextLine();

                    Color.printYellowNo("Enter description: ");
                    String description = input.nextLine();

                    ChannelService.createChannel(groupId, displayName, description);
                } else if (option.equals("8")) {
                    Color.printYellow("-- Add user/multiple users to Team --");

                    Color.printYellowNo("Enter groupId: ");
                    String groupId = input.nextLine();

                    Color.printYellowNo("Enter userIds: ");
                    List<String> userId = new ArrayList<>();
                    String line = input.nextLine();
                    while (!line.isEmpty()) {
                        userId.add(line);
                        line = input.nextLine();
                    }

                    groupService.addMemberToTeam(groupId, userId);
                } else if (option.equals("9")) {
                    Color.printYellow("-- Get user by principal name --");

                    Color.printYellowNo("Enter userPrincipalName (example@example.com): ");
                    String userPrincipalName = input.nextLine();

                    service.getUserByPrincipalName(userPrincipalName);
                } else if (option.equals("10")) {
                    Color.printYellow("-- Assign license for user --");

                    Color.printYellowNo("Enter userId: ");
                    String userId = input.nextLine();

                    service.assignLicense(userId);
                } else if (option.equals("11")) {
                    Color.printYellow("-- List existed groupId --");

                    groupService.listIDsGroup();
                } else if (option.equals("12")) {
                    Color.printYellow("-- Synchronize Users information in an organization to Airtable --");

                    // token MS
                    String token = Config.getAccessToken();


                    List<JsonObject> fields = Organization.listUsersAsJson(token);

                    //token Airtable
                    JsonObject access = JsonTool.getAccessInfo(configAirTable).getAsJsonObject();
                    String personal_access_token = access.get("personal_access_token").getAsString();
                    String baseId = access.get("baseId").getAsString();

                    Color.printYellow("Do you want to synchronize to an existed table (Press 1) or create new table and sync (Press 2)?");
                    String choose = input.nextLine();
                    if (choose.equals("1")) {
                        Color.printYellowNo("Enter tableId: ");
                        String tableId = input.nextLine();

                        JsonObject fieldTable = Table.getTable(tableId, baseId, personal_access_token);

                        Table table = new Table(fieldTable, baseId, personal_access_token);
                        table.pullAllRecords(fields, baseId, personal_access_token);
                    } else if (choose.equals("2")) {
                        Color.printYellowNo("Enter table name: ");
                        String name = input.nextLine();

                        JsonArray fieldSetting = Organization.tableSetting;

                        String response = Table.createTable(name, fieldSetting, baseId, personal_access_token);

                        JsonObject table = JsonParser.parseString(response).getAsJsonObject();

                        Table tableObj = new Table(table, baseId, personal_access_token);
                        tableObj.pullAllRecords(fields, baseId, personal_access_token);
                    }
                } else if (option.equals("13")) {
                    Color.printYellow("-- Synchronize Users information in an Team(Group) to Airtable --");

                    // token MS
                    String token = Config.getAccessToken();

                    Color.printYellowNo("Enter groupId: ");
                    String groupId = input.nextLine();

                    List<JsonObject> fields = GroupServiceImpl.listUsersAsJson(groupId, token);

                    //token Airtable
                    JsonObject access = JsonTool.getAccessInfo(configAirTable).getAsJsonObject();
                    String personal_access_token = access.get("personal_access_token").getAsString();
                    String baseId = access.get("baseId").getAsString();

                    Color.printYellow("Do you want to synchronize to an existed table (Press 1) or create new table and sync (Press 2)?");
                    String choose = input.nextLine();
                    if (choose.equals("1")) {
                        Color.printYellowNo("Enter tableId: ");
                        String tableId = input.nextLine();

                        JsonObject fieldTable = Table.getTable(tableId, baseId, personal_access_token);

                        Table table = new Table(fieldTable, baseId, personal_access_token);
                        table.pullAllRecords(fields, baseId, personal_access_token);
                    } else if (choose.equals("2")) {
                        Color.printYellowNo("Enter table name: ");
                        String name = input.nextLine();

                        JsonArray fieldSetting = GroupServiceImpl.tableSetting;

                        String response = Table.createTable(name, fieldSetting, baseId, personal_access_token);

                        JsonObject table = JsonParser.parseString(response).getAsJsonObject();

                        Table tableObj = new Table(table, baseId, personal_access_token);
                        tableObj.pullAllRecords(fields, baseId, personal_access_token);
                    }
                } else if (option.equals("14")) {
                    Color.printYellow("-- Write table to XLSX file --");

                    //token Airtable
                    JsonObject access = JsonTool.getAccessInfo(configAirTable).getAsJsonObject();
                    String personal_access_token = access.get("personal_access_token").getAsString();
                    String baseId = access.get("baseId").getAsString();

                    Color.printYellowNo("Enter tableId: ");
                    String tableId = input.nextLine();

                    Color.printYellowNo("Enter filepath/filename: ");
                    String filepath = input.nextLine();

                    JsonObject fieldTable = Table.getTable(tableId, baseId, personal_access_token);

                    Table table = new Table(fieldTable, baseId, personal_access_token);

                    table.writeTableToXLSX(filepath, baseId, personal_access_token);
                } else if (option.equals("15")) {
                    Color.printGreen(general);
                } else if (option.equals("16")) {
                    break;
                } else {
                    continue;
                }

                Color.printGreen("Do you want to continue using ANTI-RICONS application? Y/N");
                String choosing = input.nextLine();
                if (!choosing.equals("Y")) {
                    break;
                }
            }
        }
        catch (IOException | InterruptedException e)
        {
            Thread.currentThread().interrupt();
            System.out.println("Oops! We ran into some problems");
        }
    }

    public static void main(String[] args){
        showMenu();
    }
}

