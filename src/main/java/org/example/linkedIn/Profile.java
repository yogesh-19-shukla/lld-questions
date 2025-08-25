package org.example.linkedIn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Profile {

    final String userId;
    String pictureUrl;
    String headline;
    String summary;
    List<String> experience = new ArrayList<>();
    List<String> education  = new ArrayList<>();
    Set<String> skills = new HashSet<>();

    Profile(String userId) {
        this.userId = userId;
    }
}
