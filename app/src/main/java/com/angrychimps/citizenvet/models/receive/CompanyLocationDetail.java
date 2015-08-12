package com.angrychimps.citizenvet.models.receive;

import com.angrychimps.citizenvet.models.Reviews;
import com.angrychimps.citizenvet.models.Staff;
import com.angrychimps.citizenvet.models.shared.Company;
import com.angrychimps.citizenvet.models.shared.CompanyLocation;

/*
    Received by Location API from a GET request
 */
public class CompanyLocationDetail {
    private CompanyLocation location;
    private Company company;
    private Staff staff;
    private Reviews reviews;

    public CompanyLocationDetail() {
    }

    public CompanyLocation getLocation() {
        return location;
    }

    public void setLocation(CompanyLocation location) {
        this.location = location;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }
}
