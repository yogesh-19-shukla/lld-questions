package org.example.linkedIn;

public class ProfileService {

    private final Stores stores;

    ProfileService(Stores stores) { this.stores = stores; }

    public Profile view(String userId) { return stores.profiles.get(userId); }

    public void updateHeadline(String userId, String headline) {
        stores.profiles.get(userId).headline = headline;
    }
    public void addSkill(String userId, String skill) {
        stores.profiles.get(userId).skills.add(skill.toLowerCase());
    }
    public void addExperience(String userId, String exp) {
        stores.profiles.get(userId).experience.add(exp);
    }
}
