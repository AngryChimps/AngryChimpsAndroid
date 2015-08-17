package com.angrychimps.citizenvet.models.shared;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/*
    Used in Auth API, received as part of login response
    Used in Company API??
    Used in Location API, both for send and inside LocationDetail in GET receive
 */
public class CompanyLocation {
    private String id; //This may be named company_id in parts?
    private String name;
    private String description;
    private Address address;
    @SerializedName("is_mobile") private boolean isMobile;
    @SerializedName("mobile_radius_miles") private float mobileRadiusMiles;
    private List<String> photos;
    private int[] services;
    @SerializedName("walk_ins") private boolean walkIns;
    private boolean emergencies;
    private int[] animals;
    @SerializedName("staff_ids") private List<String> staffIds;
    private Hours hours;
    private int status;

    public CompanyLocation() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isMobile() {
        return isMobile;
    }

    public void setIsMobile(boolean isMobile) {
        this.isMobile = isMobile;
    }

    public float getMobileRadiusMiles() {
        return mobileRadiusMiles;
    }

    public void setMobileRadiusMiles(float mobileRadiusMiles) {
        this.mobileRadiusMiles = mobileRadiusMiles;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public int[] getServices() {
        return services;
    }

    public void setServices(int[] services) {
        this.services = services;
    }

    public boolean isWalkIns() {
        return walkIns;
    }

    public void setWalkIns(boolean walkIns) {
        this.walkIns = walkIns;
    }

    public boolean isEmergencies() {
        return emergencies;
    }

    public void setEmergencies(boolean emergencies) {
        this.emergencies = emergencies;
    }

    public int[] getAnimals() {
        return animals;
    }

    public void setAnimals(int[] animals) {
        this.animals = animals;
    }

    public List<String> getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(List<String> staffIds) {
        this.staffIds = staffIds;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public static class Hours {
        private Day sunday;
        private Day monday;
        private Day tuesday;
        private Day wednesday;
        private Day thursday;
        private Day friday;
        private Day saturday;

        public Hours() {
        }

        public Day getSunday() {
            return sunday;
        }

        public void setSunday(Day sunday) {
            this.sunday = sunday;
        }

        public Day getMonday() {
            return monday;
        }

        public void setMonday(Day monday) {
            this.monday = monday;
        }

        public Day getTuesday() {
            return tuesday;
        }

        public void setTuesday(Day tuesday) {
            this.tuesday = tuesday;
        }

        public Day getWednesday() {
            return wednesday;
        }

        public void setWednesday(Day wednesday) {
            this.wednesday = wednesday;
        }

        public Day getThursday() {
            return thursday;
        }

        public void setThursday(Day thursday) {
            this.thursday = thursday;
        }

        public Day getFriday() {
            return friday;
        }

        public void setFriday(Day friday) {
            this.friday = friday;
        }

        public Day getSaturday() {
            return saturday;
        }

        public void setSaturday(Day saturday) {
            this.saturday = saturday;
        }


        public static class Day {
            private Date start;
            private Date end;

            public Day() {
            }

            public Date getStart() {
                return start;
            }

            public void setStart(Date start) {
                this.start = start;
            }

            public Date getEnd() {
                return end;
            }

            public void setEnd(Date end) {
                this.end = end;
            }
        }
    }
}
