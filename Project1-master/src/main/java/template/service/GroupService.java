package template.service;

import java.util.List;

public interface GroupService {
    void createGroup(String description , String displayName , String mailNickname , String ownerId , List<String> userId);
    void createTeam(String groupId);
    void deleteTeam(String groupId);
}
