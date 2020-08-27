package com.trivago.hotel.util;

import com.trivago.hotel.exception.OfferNotFoundException;
import com.trivago.hotel.model.Offer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * One Direction LinkedList of Offers sorted by availabilityStartDate
 */
public class OfferList {
    private Node head;

    private class Node {
        Offer offer;
        Node next;

        public Node(Offer offer, Node next) {
            this.offer = offer;
            this.next = next;
        }
    }

    /**
     * adds newOffer to OfferList in a way that OfferList remains sorted
     *
     * @param newOffer Offer to be added to OfferList
     */
    public synchronized void addOffer(Offer newOffer) {
        Node newNode = new Node(newOffer, null);
        if (head == null) {
            head = newNode;
        } else {
            if (head.offer.getAvailabilityStartDate() >= newNode.offer.getAvailabilityStartDate()) {
                newNode.next = head;
                head = newNode;
            } else {
                Node previous = head;
                Node current = head.next;
                while (current != null && current.offer.getAvailabilityStartDate() < newNode.offer.getAvailabilityStartDate()) {
                    previous = current;
                    current = current.next;
                }
                newNode.next = current;
                previous.next = newNode;
            }
        }
    }

    public Offer getByAdvertiserIdAdnStartDateAndEndDate(int advertiserId, int startDate, int endDate) {
        Node current = head;

        while (current != null && current.offer.getAvailabilityStartDate() <= startDate) {
            Offer offer = current.offer;

            if (offer.getAdvertiser().getId() == advertiserId
                    && offer.getAvailabilityStartDate() == startDate && offer.getAvailabilityEndDate() == endDate) {
                return offer;
            }
            current = current.next;
        }
        throw new OfferNotFoundException("advertiserId: " + advertiserId + ", startDate: " + startDate + ", endDate: " + endDate);
    }

    public List<Offer> getByStartDateAndEndDate(int startDate, int endDate) {
        List<Offer> result = new LinkedList<>();

        Node current = head;

        while (current != null) {
            Offer currentOffer = current.offer;

            if (currentOffer.getAvailabilityStartDate() > startDate) {
                break;
            }
            if (currentOffer.getAvailabilityStartDate() <= startDate
                    && currentOffer.getAvailabilityEndDate() >= endDate) {
                result.add(currentOffer);
            }
            current = current.next;
        }
        return result;
    }

    public synchronized void removeExpiredOffers() {
        if (head == null) {
            return;
        }
        String dateString = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);

        Node previous = head;
        Node current = head.next;

        while (current != null) {
            if (current.offer.getAvailabilityEndDate() < Integer.parseInt(dateString)) {
                previous.next = current.next;
            } else {
                previous = current;
            }
            current = current.next;
        }

        if (head.offer.getAvailabilityEndDate() < Integer.parseInt(dateString)) {
            head = head.next;
        }
    }
}
