package com.angrychimps.citizenvet.models.received;

import com.angrychimps.citizenvet.models.shared.Company;
import com.angrychimps.citizenvet.models.shared.Member;

import java.util.List;

/*
    Used in Auth API, received after login
 */
public class UserLoginResponse {
    private Member member;
    private List<Company> companies;

    public UserLoginResponse() {
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}
