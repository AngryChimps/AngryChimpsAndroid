package com.angrychimps.citizenvet.models.receive;

import com.angrychimps.citizenvet.models.shared.Company;
import com.angrychimps.citizenvet.models.shared.CompanyLocation;
import com.angrychimps.citizenvet.models.shared.StaffMember;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    public static class Staff{
        private int count;
        @SerializedName("results") private List<StaffMember> staffMembers;

        public Staff() {
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<StaffMember> getStaffMembers() {
            return staffMembers;
        }

        public void setStaffMembers(List<StaffMember> staffMembers) {
            this.staffMembers = staffMembers;
        }
    }

    public static class Reviews {
        private int count;
        @SerializedName("results") private List<ReviewDetail> reviews;

        public Reviews() {
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ReviewDetail> getReviews() {
            return reviews;
        }

        public void setReviews(List<ReviewDetail> reviews) {
            this.reviews = reviews;
        }
    }
}
