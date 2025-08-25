package org.example.libraryManagement;

import java.util.List;

public interface SearchStrategy {
    List<LibraryItem> search(String query, List<LibraryItem> items);
}