package template.persistence.dto;

import com.microsoft.graph.models.PasswordProfile;

public class Member {
    private String displayName;
    private String mailNickname;
    private String memberPrincipalName;
    private PasswordProfile passwordProfile;
    private boolean accountEnabled;
    public Member(){}
    public Member (String displayName ,String mailNickname ,String memberPrincipalName ,PasswordProfile passwordProfile ){
        this.displayName = displayName;
        this.mailNickname = mailNickname;
        this.memberPrincipalName = memberPrincipalName;
        this.passwordProfile = passwordProfile;
    }

    public boolean isAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMailNickname() {
        return mailNickname;
    }

    public void setMailNickname(String mailNickname) {
        this.mailNickname = mailNickname;
    }

    public String getMemberPrincipalName() {
        return memberPrincipalName;
    }

    public void setMemberPrincipalName(String memberPrincipalName) {
        this.memberPrincipalName = memberPrincipalName;
    }

    public PasswordProfile getPasswordProfile() {
        return passwordProfile;
    }

    public void setPasswordProfile(PasswordProfile passwordProfile) {
        this.passwordProfile = passwordProfile;
    }
}
