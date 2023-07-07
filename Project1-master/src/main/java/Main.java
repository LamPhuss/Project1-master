
import template.service.GroupServiceImpl;
import template.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) throws Exception{
        UserServiceImpl service = new UserServiceImpl();
        /*
        //test get user
        service.getUser();*/

        //test create user
        /*
        service.createUser("John Doe" , "johndoe" ,"johndoe@3pjv85.onmicrosoft.com" ,"P@ssw0rd123");

         */
        GroupServiceImpl service2 = new GroupServiceImpl();
        /*
        //test create group
        String des = "Group with designated owner and members";
        String display = "My group";
        String mailNick = "phusgroup2023";
        String ownerId = "66ebefba-651f-4fe7-ab39-2b3a29b5643c";
        List<String> userIds = new ArrayList<>();
        userIds.add("6301cd42-30ba-476e-aca5-ad1fae558b0b");
        userIds.add("88ff6ec2-3b5d-4b56-b013-ebd683870604");
        service2.createGroup(des,display,mailNick,ownerId,userIds);
        */
        /*
        //test create team
        service2.createTeam("0c1c2f6a-fe30-4d58-9029-932834a43b7e");
        */
        //test create multiple user from csv
        service.createAllUsers("users.csv");

    }
}