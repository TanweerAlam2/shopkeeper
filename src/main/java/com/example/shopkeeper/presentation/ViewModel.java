package com.example.shopkeeper.presentation;

import java.util.Map;

/**
 * Small view-model helper for server-rendered Thymeleaf pages.
 */
public record ViewModel(String pageTitle, String activePage, Map<String, Object> values) {
    public static ViewModel page(String title, String activePage) {
        return new ViewModel(title, activePage, Map.of());
    }
}
