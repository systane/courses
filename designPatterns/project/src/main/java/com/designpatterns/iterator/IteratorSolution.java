package com.designpatterns.iterator;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Iterator;

public class IteratorSolution {

    @AllArgsConstructor
    @Getter
    private static  class Notification {
        private String notification;
    }

    private interface Collection{
        Iterator createIterator();
    }

    private static class NotificationCollection implements Collection{

        private static final int MAX_ITENS = 6;
        int numberOfItems = 0;
        Notification[] notificationList;

        private NotificationCollection() {
            notificationList = new Notification[MAX_ITENS];

            addItem("Notification 1");
            addItem("Notification 2");
            addItem("Notification 3");
        }

        private void addItem(String str){
            Notification notification = new Notification(str);
            if(numberOfItems >= MAX_ITENS)
                System.err.println("FULL");
            else{
                notificationList[numberOfItems] = notification;
                numberOfItems = numberOfItems + 1;
            }
        }

        @Override
        public Iterator createIterator() {
            return new NotificationIterator(notificationList);
        }
    }


    public static class NotificationIterator implements Iterator{
        private Notification[] notificationList;
        int pos = 0;// maintains curr pos of iterator over the array

        private NotificationIterator(Notification[] notificationList) {
            this.notificationList = notificationList;
        }

        @Override
        public boolean hasNext() {
            return pos < notificationList.length &&
                    notificationList[pos] != null;
        }

        @Override
        public Object next() {
            // return next element in the array and increment pos
            Notification notification =  notificationList[pos];
            pos += 1;
            return notification;
        }
    }


}
