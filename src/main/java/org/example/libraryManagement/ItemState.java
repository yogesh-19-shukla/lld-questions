package org.example.libraryManagement;

public interface ItemState {
    void checkout(BookCopy copy, Member member);
    void returnItem(BookCopy copy);
    void placeHold(BookCopy copy, Member member);
}