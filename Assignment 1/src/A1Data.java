/**
 * A1Data.java
 * COMP 1020 Fall 2026 
 * Assignment 1 - Comp1020Parcel
 * Initial data for the system. 
 * Do not modify this file.
 */
public class A1Data {

    // ---------- Hubs ----------
    public static final String[] HUB_CODES = {
        "WPG", "YYZ", "YVR", "YUL", "JFK", "LAX", "LHR", "NRT"
    };

    public static final String[] HUB_CITIES = {
        "Winnipeg", "Toronto", "Vancouver", "Montreal",
        "New York", "Los Angeles", "London", "Tokyo"
    };

    public static final String[] HUB_COUNTRIES = {
        "Canada", "Canada", "Canada", "Canada",
        "USA", "USA", "UK", "Japan"
    };

    public static final int[] HUB_FEES = {
        5, 8, 7, 6, 10, 12, 15, 0
    };

    // ---------- Shipments ----------
    public static final String[] SHIPMENT_ORIGINS = {
        "WPG", "WPG", "WPG", "WPG", "YYZ", "YVR",
        "YYZ", "JFK", "YUL", "LAX", "WPG", "LHR"
    };

    public static final String[] SHIPMENT_DESTINATIONS = {
        "YYZ", "YYZ", "YYZ", "JFK", "WPG", "LAX",
        "LHR", "LAX", "NRT", "WPG", "YVR", "YYZ"
    };

    public static final int[] SHIPMENT_PRICES = {
        40, 35, 35, 90, 45, 110,
        250, 75, 333, 101, 50, 199
    };

    // ---------- Customers ----------
    public static final String[] CUSTOMER_NAMES = {
        "Alice", "Bob", "Chen", "Dana"
    };

    public static final String[] CUSTOMER_COUNTRIES = {
        "Canada", "USA", "Canada", "UK"
    };

    public static final int[] CUSTOMER_YEARS_JOINED = {
        2015, 2024, 2021, 2022
    };
}