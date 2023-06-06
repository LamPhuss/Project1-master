package template.service;
/*
import com.microsoft.graph.models.PasswordProfile;
import template.persistence.dto.Member;
import com.microsoft.graph.requests.GraphServiceClient;
import com.opencsv.CSVReader;

import java.io.FileReader;
import template.api_config.config.*;
import java.util.ArrayList;
import java.util.List;

public class MemberService {

    private template.persistence.dto.Member Member;
    static template.api_config.config graphClient;

    public static List<Member> inputMemberList(String filePath) throws Exception {
        List<Member> members = new ArrayList<>();

        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            Member member = new Member();
            member.setDisplayName(nextLine[0]);
            member.setMailNickname(nextLine[1]);
            member.setMemberPrincipalName(nextLine[2]);
            member.setPasswordProfile(nextLine[3]);
            members.add(member);
        }

        return members;
    }

    public static void createUser() throws Exception{
        Member member = new Member();
        member.setAccountEnabled(true);
        member.setDisplayName("Adele Vance");
        member.setMailNickname("AdeleV");
        member.setMemberPrincipalName("AdeleV@contoso.onmicrosoft.com");
        PasswordProfile passwordProfile = new PasswordProfile();
        passwordProfile.forceChangePasswordNextSignIn = true;
        passwordProfile.password = "xWwvJ]6NMw+bWH-d";
        member.setPasswordProfile(passwordProfile);

        graphClient.users()
                .buildRequest()
                .post(member);

    }
}*/
